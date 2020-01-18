function updateLanguage(el) {
    var selectedLanguage = el.value;
    var key = "lang";
    insertParam(key,selectedLanguage);
}


function insertParam(key, value) {
var searchParams = new URLSearchParams(window.location.search)
  searchParams.set(key, value)
  window.location.search = searchParams.toString()
  }