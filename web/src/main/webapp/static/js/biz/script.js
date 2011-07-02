Biz.Script = {};

Biz.Script.extractScript = function(domHtml) {
	var hd = document.getElementsByTagName("head")[0];
    var re = /(?:<script([^>]*)?>)((\n|\r|.)*?)(?:<\/script>)/ig;
    var srcRe = /\ssrc=([\'\"])(.*?)\1/i;
    var typeRe = /\stype=([\'\"])(.*?)\1/i;

    var match;
    while(match = re.exec(domHtml)){
        var attrs = match[1];
        var srcMatch = attrs ? attrs.match(srcRe) : false;
        if (srcMatch && srcMatch[2]) {
           var s = document.createElement("script");
           s.src = srcMatch[2];
           var typeMatch = attrs.match(typeRe);
           if (typeMatch && typeMatch[2]) {
               s.type = typeMatch[2];
           }
           hd.appendChild(s);
        } else if (match[2] && match[2].length > 0) {
            if (window.execScript) {
               window.execScript(match[2]);
            } else {
               window.eval(match[2]);
            }
        }
    }

    return domHtml.replace(/(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig, "");
}