var services = angular.module('chat.services', []);

// share user information across controllers
services.factory('socket', function ($rootScope) {
//    var socket = io.connect('http://111.206.45.12:30021/');
        var socket = io.connect('/');
    return {
        on:function(eventName,callback){
            socket.on(eventName,function(){
                var args = arguments;
                $rootScope.$apply(function(){
                    callback.apply(socket,args);
                })
            })
        },
        emit:function(eventName,data,callback){
            socket.emit(eventName,data,function(){
                var args = arguments;
                $rootScope.$apply(function(){
                    if(callback){
                        callback.apply(socket,args);
                    }
                })
            })
        }

    };

});