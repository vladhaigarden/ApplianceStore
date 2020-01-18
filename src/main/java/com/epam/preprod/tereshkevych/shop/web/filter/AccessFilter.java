package com.epam.preprod.tereshkevych.shop.web.filter;

import com.epam.preprod.tereshkevych.shop.db.entity.Role;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AccessFilter.class);

    private static final String ERROR_PARSER = "Cannot parse file.xml";

    private static final String ERROR_APP_EXCEPTION = "appError";

    private static final String ERROR_PERMISSION = "You do not have permission to access the requested resource";

    private static final String SLASH = "/";

    private static final String PARAMETER_CONSTRAINT_XML = "pathConstraint";

    private static final String NODE_NAME_URL = "url-pattern";
    private static final String NODE_NAME_ROLE = "role";

    private static final String ATTRIBUTE_USER = "user";

    private static final String PAGE_LOGIN_USER = "login";

    private static final String PAGE__ERROR_PAGE = "http://localhost:8888/app_error_page.jsp";

    private static final String LAST_PAGE = "referer";

    private Map<String, List<Role>> pagesRoles;

    @Override
    public void init(FilterConfig filterConfig) {
        String pathConstraint = filterConfig.getInitParameter(PARAMETER_CONSTRAINT_XML);
        try {
            pagesRoles = getConstraintAfterParsing(pathConstraint);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOG.error(ERROR_PARSER);
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String selectedUrl = String.valueOf(request.getRequestURL());
        String selectedPath = selectedUrl.substring(selectedUrl.lastIndexOf(SLASH));
        if (pagesRoles.containsKey(selectedPath)) {
            User user = (User) session.getAttribute(ATTRIBUTE_USER);
            if (user == null) {
                session.setAttribute(LAST_PAGE, selectedUrl);
                response.sendRedirect(PAGE_LOGIN_USER);
                return;
            }
            if (!pagesRoles.get(selectedPath).contains(user.getRole())) {
                session.setAttribute(ERROR_APP_EXCEPTION, ERROR_PERMISSION);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendRedirect(PAGE__ERROR_PAGE);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Map<String, List<Role>> getConstraintAfterParsing(String path) throws ParserConfigurationException, IOException, SAXException {
        Map<String, List<Role>> pagesRoles = new HashMap<>();
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
        NodeList constraints = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < constraints.getLength(); i++) {
            Node constraint = constraints.item(i);
            if (constraint.getNodeType() != Node.TEXT_NODE) {
                NodeList constraintProps = constraint.getChildNodes();
                fillPagesRoles(pagesRoles, constraintProps);
            }
        }
        return pagesRoles;
    }

    private void fillPagesRoles(Map<String, List<Role>> pagesRoles, NodeList constraintProps) {
        List<Role> roles = new ArrayList<>();
        String key = null;
        for (int j = 0; j < constraintProps.getLength(); j++) {
            Node constraintProp = constraintProps.item(j);
            if (constraintProp.getNodeType() != Node.TEXT_NODE) {
                String nodeName = constraintProp.getNodeName();
                String nameChildNode = constraintProp.getChildNodes().item(0).getTextContent();
                switch (nodeName) {
                    case NODE_NAME_URL:
                        key = nameChildNode;
                        break;
                    case NODE_NAME_ROLE:
                        roles.add(Role.valueOf(nameChildNode.toUpperCase()));
                }
            }
        }
        pagesRoles.put(key, roles);
    }

    @Override
    public void destroy() {
    }
}