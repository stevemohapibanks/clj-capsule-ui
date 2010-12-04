var CapsuleApp = (function($) {
  
  var public_properties = {};
  
  function append_search_results(results) {
    $.each(results, function(i, val) {
      if (val.type == "person") {
        $('#available').append(render_person(val));
      } else if (val.type == "organisation") {
        $('#available').append(render_organisation(val));
      }
    })
    update_drag_and_drop();
  }
  
  function render_person(person) {
    return $("<li id=\"" + person.id + "\" class=\"draggable\">" +
        "<ul class=\"party person\">" +
          "<li class=\"picture\"><img src=\"" + person.pictureURL + "\"></img></li>" +
          "<li class=\"name\">" + person.firstName + " " + person.lastName + "</li>" +
        "</ul>" +
      "</li>");
  }

  function render_organisation(org) {
    return $("<li class=\"draggable\">" +
        "<ul class=\"party org\">" +
          "<li class=\"picture\"><img src=\"" + org.pictureURL + "\"></img></li>" +
          "<li class=\"name\">" + org.name + "</li>" +
        "</ul>" +
      "</li>");
  }
  
  function register_submit_find() {
    $('#query-form').submit(function() {
      $.getJSON("/find/" + $('#query').val(), function(data) {
        append_search_results(data);
        $('#query').val('');
      });
      return false;
    });
  }
  
  function register_submit_clear() {
    $('#clear').click(function() {
      $('ul#available').empty();
      $('#query').val('');
      return false;
    });
  }
  
  function setup_drag_and_drop() {
    $('#available').droppable({
      accept: '#selected>li',
      activeClass: 'highlight',
      drop: function(event, item) {
        item.draggable.appendTo($('ul#available'))
      }
    });
    $('#selected').droppable({
      accept: '#available>li',
      activeClass: 'highlight',
      drop: function(event, item) {
        item.draggable.appendTo($('ul#selected'))
      }
    });
  }
  
  function update_drag_and_drop() {
    $('.draggable').draggable({
      containment: '#container',
      revert: 'invalid',
      helper: 'clone'
    });
  }
  
  function select_party() {
    
  }
  
  function unselect_party() {
    
  }
  
  public_properties.init = function() {
    register_submit_find();
    register_submit_clear();
    setup_drag_and_drop();
    $('#query').focus();
  }
  
  return public_properties;
} (jQuery));


$(function() { CapsuleApp.init(); });
