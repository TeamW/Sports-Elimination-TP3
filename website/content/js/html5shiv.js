(function(p,f){function q(){var a=g.elements;return"string"==typeof a?a.split(" "):a}function i(a){var b=r[a[s]];b||(b={},j++,a[s]=j,r[j]=b);return b}function t(a,b,c){b||(b=f);if(h)return b.createElement(a);c||(c=i(b));b=c.cache[a]?c.cache[a].cloneNode():v.test(a)?(c.cache[a]=c.createElem(a)).cloneNode():c.createElem(a);return b.canHaveChildren&&!w.test(a)?c.frag.appendChild(b):b}function u(a){a||(a=f);var b=i(a);if(g.shivCSS&&!k&&!b.hasCSS){var c,d=a;c=d.createElement("p");d=d.getElementsByTagName("head")[0]||
d.documentElement;c.innerHTML="x<style>article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}</style>";c=d.insertBefore(c.lastChild,d.firstChild);b.hasCSS=!!c}if(!h){var e=a;b.cache||(b.cache={},b.createElem=e.createElement,b.createFrag=e.createDocumentFragment,b.frag=b.createFrag());e.createElement=function(a){return!g.shivMethods?b.createElem(a):t(a,e,b)};e.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+
q().join().replace(/\w+/g,function(a){b.createElem(a);b.frag.createElement(a);return'c("'+a+'")'})+");return n}")(g,b.frag)}return a}var l=p.html5||{},w=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,v=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,k,s="_html5shiv",j=0,r={},h;try{var m=f.createElement("a");m.innerHTML="<xyz></xyz>";k="hidden"in m;var n;if(!(n=1==m.childNodes.length)){f.createElement("a");var o=f.createDocumentFragment();
n="undefined"==typeof o.cloneNode||"undefined"==typeof o.createDocumentFragment||"undefined"==typeof o.createElement}h=n}catch(x){h=k=!0}var g={elements:l.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",shivCSS:!1!==l.shivCSS,supportsUnknownElements:h,shivMethods:!1!==l.shivMethods,type:"default",shivDocument:u,createElement:t,createDocumentFragment:function(a,b){a||(a=f);if(h)return a.createDocumentFragment();
for(var b=b||i(a),c=b.frag.cloneNode(),d=0,e=q(),g=e.length;d<g;d++)c.createElement(e[d]);return c}};p.html5=g;u(f)})(this,document);