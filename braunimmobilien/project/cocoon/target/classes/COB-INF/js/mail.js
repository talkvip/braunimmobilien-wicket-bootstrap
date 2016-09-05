/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
var onMailSend = function(data){
   $("#serverResponse").html("Result: " + data.status);
   //Emabling button
   $("#sendMail").removeAttr('disabled');
};
var onMailSendPipe = function(data){
   $("#serverResponsePipe").html("Result: " + data.status);
   //Emabling button
   $("#sendMailPipe").removeAttr('disabled');
};

$(document).ready(function(){
    //Button click event
    $("#sendMail").click(function(e){
        $("#serverResponse").html("Processing request...");
        e.preventDefault();
        //Disabling button
        $("#sendMail").attr('disabled', 'disabled');
        // get the input data
        var to = $("#mailto").val();
        var from = $("#mailfrom").val();
        var subject = $("#mailsubject").val();
        var text = $("#mailtext").val();
        //Perform POST for triggering long running sendMail
        $.post('rest/SendMailService', {
            from: from,
            to: to,
            text: text,
            subject: subject
        }, function(data){
            onMailSend(data);
        }, "json");
    });
    $("#sendMailPipe").click(function(e){
        $("#serverResponsePipe").html("Processing request...");
        e.preventDefault();
        //Disabling button
        $("#sendMailPipe").attr('disabled', 'disabled');
        // get the input data
        var name = $("#mailname").val();
        //Perform POST for triggering long running sendMail
        $.post('rest/SendMailPipeService', {
            name: name
        }, function(data){
            onMailSendPipe(data);
        }, "json");
    });
});
