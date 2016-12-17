/**
 * Created by liwei_000 on 2014/7/19.
 */
var app = angular.module('chat.directives', []);

app.directive('autoScrollToBottom', function () {
    return{
        link: function (scope, element, attrs) {
            scope.$watch(
                function () {
                    return element.children().length;
                },
                function () {
                    element.animate({
                        scrollTop: element.prop('scrollHeight')
                    }, 200);
                }
            )
        }
    }
});
app.directive('ctrlEnterBreakLine', function () {
    return function (scope, element, attrs) {
        var ctrlDown = false;
        element.bind("keydown", function (evt) {
            if (evt.which === 17) {
                ctrlDown = true;
                setTimeout(function () {
                    ctrlDown = false;
                }, 1000);
            }
            if (evt.which === 13) {
                if (ctrlDown) {
                    element.val(element.val() + '\n');

                } else {
                    scope.$apply(function () {
                        scope.$eval(attrs.ctrlEnterBreakLine);

                    });
                    evt.preventDefault();
                }
            }
        })
    }
})