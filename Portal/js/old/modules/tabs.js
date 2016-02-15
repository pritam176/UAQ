// --------------------------------------------------
// Tabs switcher
// --------------------------------------------------

(function ($) {
 
  $.fn.tabs = function (options) {

    var settings = $.extend({
      tabsTarget: 'data-tab',// data attibute for pane id
      item: '_tabItem', // class of tabs nav item
      before: function(pane, item) {},//execute function before actions
      after: function (pane, item) { }, // execute function after actions
      whenCliked: function (pane, item) { }
    }, options);

    this.each(function () {
      var groupClass = '_group' + (Math.floor(Math.random() * 999) + 100);
      var items = $(this).find('.' + settings.item);
      items.map(function () {
        $($(this).attr(settings.tabsTarget)).addClass(groupClass);
      });
      items.first().addClass('active');
      $(items.first().attr(settings.tabsTarget)).addClass('active').show();

      items.on('click.tab.item', function(e){
        e.preventDefault();
        if($(this).hasClass('active')) return;
        var target = $($(this).attr(settings.tabsTarget));
        var group = $('.'+groupClass);
        var that = $(this);
        //callback function before actions
        if (typeof settings.before === "function") {
          settings.before(target, that);
        }//if

        group.hide().removeClass('active');
        target.fadeIn(200, function () {
          if (typeof settings.after === "function") {
            settings.after(target, that);
          }//if
        }).addClass('active');
        if (typeof settings.whenCliked === "function") {
          settings.whenCliked(target, that);
        }//if
        items.removeClass('active');
        that.addClass('active');
      });

    });//each

    return this;
  };//dropdown

})(jQuery);
