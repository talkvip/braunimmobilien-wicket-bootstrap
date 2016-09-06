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
var delayedReset = function(){
    //Enable button
    $("#operation").removeAttr('disabled');
    $("#header").hide();
    $("#progressbar").hide();
};
var onSuccessImport = function(data){
    $("#header").html("Invoked processing");
    //Updating progress
    $("#progressbar").progressbar('value',data.progress);
    //Setting the timer
    window.progressIntervalId = window.setInterval(function(){
        //Getting current operation progress
        $.get('rest/ProgressService', function(data){
            //Updating progress
            $("#progressbar").progressbar('value', data.progress);
            //If operation is complete
            if (data.progress == 100) {
                //Clear timer
                window.clearInterval(window.progressIntervalId);
                $("#header").html("Processing finished.");
                setTimeout("delayedReset()", 3000);
            }
        });
    }, 500);
};

$(document).ready(function(){
    $('#ProgressServiceFormResult').hide();
    //Progressbar initialization
    $("#progressbar").progressbar({
        value: 0
    });
    //Button click event
    $("#operation").click(function(e){
        $("#header").html("Processing request...");
        $("#header").show();
        $('#ProgressServiceFormResult').show();
        $("#progressbar").show();
        // disable the form submit
        e.preventDefault();
        //Disabling button
        $("#operation").attr('disabled', 'disabled');
        //Making sure that progress indicate 0
        $("#progressbar").progressbar('value', 0);
        // get the input data
        var input = $("#nameImport").val();
        //Perform POST for triggering long running operation
        $.post('rest/ProgressService', {
            name: input
        }, function(data){
            onSuccessImport(data);
        }, "json");
    });
});
