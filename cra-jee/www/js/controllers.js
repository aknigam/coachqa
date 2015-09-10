angular.module('starter.controllers', [])

.controller('AskQuestionCtrl', function($scope, Chats) {
    $scope.isPublic = false;
    $scope.subject = 'Mathematics';
    $scope.questionTitle="mytest";
    $scope.questionContent="123";

    $scope.submit = function(){
      var data = {};
      data.questionId = null;
      data.refSubjectId = 1;
      data.questionLevelId = 0;
      data.postedBy =1;
      data.refQuestionStatusId =1;
      data.title = $scope.questionTitle;
      data.content = $scope.questionContent;
      data.noOfViews = 7;
      data.postDate = new Date();
      data.lastActiveDate = new Date();
      data.votes = 0;
      data.isPublic = $scope.isPublic;
      data.public = $scope.isPublic;
      data.classroom = null;
      Chats.postQuestion(data).then(function(response){
        alert('Question ' + $scope.questionTitle + ' posted successfully.....')
      });
    }


    })

.controller('ChatsCtrl', function($scope, Chats) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});
  Chats.getConfig();
  $scope.chats = Chats.all();
  $scope.remove = function(chat) {
    Chats.remove(chat);
  };
})

.controller('ChatDetailCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
})

.controller('AccountCtrl', function($scope) {
  $scope.settings = {
    enableFriends: true
  };
});
