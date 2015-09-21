angular.module('starter.controllers', [])

.controller('AskQuestionCtrl', function($scope, Chats) {
    $scope.formInputs = {
               isPublic : false,
               subject: 'Mathematics',
               questionTitle: "Sample question title",
               questionContent: "This is my first question"
              };

    $scope.submit = function(){
      var data = {};
      data.questionId = null;
      data.refSubjectId = 1;
      data.questionLevelId = 0;
      data.postedBy =1;
      data.refQuestionStatusId =1;
      data.title = $scope.formInputs.questionTitle;
      data.content = $scope.formInputs.questionContent;
      data.noOfViews = 7;
      data.postDate = "2015-08-25",
      data.lastActiveDate = "2015-08-25";
      data.votes = 0;
      data.isPublic = $scope.formInputs.isPublic;
      data.public = $scope.formInputs.isPublic;
      data.classroom = null;
      Chats.postQuestion(data).then(function(response){
        alert('Question ' + $scope.formInputs.questionTitle + ' posted successfully.....')
      }).error(function(error){
        alert('Question ' + $scope.formInputs.questionTitle + ' posted successfully..... with error' + error);
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
})

    .controller('LoginCtrl', function($scope, LoginService, $ionicPopup, $state) {
        $scope.data = {};

        $scope.login = function() {
            LoginService.loginUser($scope.data.username, $scope.data.password).success(function(data) {
                $state.go('tab.question');
            }).error(function(data) {
                var alertPopup = $ionicPopup.alert({
                    title: 'Login failed!',
                    template: 'Please check your credentials!'
                });
            });
        }
    });;
