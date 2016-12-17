var app = angular.module('chat.controllers', []);

/**
 * Main / Root controller
 */
app.controller('RoomCtrl', function ($scope, socket) {
    $scope.messages = [];
    socket.emit('getAllMessages');
    socket.on('allMessages', function (messages) {
        $scope.messages = messages;
        console.log(messages);
    });
    socket.on('messageAdded', function (message) {
        $scope.messages.push(message);

    });
});
app.controller('MessageCreatorCtrl', function ($scope, socket) {
    $scope.newMessage = '';
    $scope.createMessage = function () {
        console.log($scope.newMessage);
        if ($scope.newMessage == '') {
            return;
        }else{
            console.log($scope.newMessage + "---------");
            socket.emit('createMessage', $scope.newMessage);
            $scope.newMessage = '';
        }

    }
});


