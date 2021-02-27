$(function(){
    $('.sort-table').tablesorter({
            headers:
            {
                0:{sorter:false},
                1:{sorter:false},
                2:{sorter:false},
            },
        textExtraction: function(node){
            var attr = $(node).attr('data-value');
            if(typeof attr !== 'undefined' && attr !== false){
                return attr;
            }
            return $(node).text();
        }
    });
});