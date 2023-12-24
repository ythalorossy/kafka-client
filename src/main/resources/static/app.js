const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/kafka-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    // stompClient.subscribe('/topic/greetings', (greeting) => {
    //     showGreeting(JSON.parse(greeting.body).content);
    // });

    stompClient.subscribe('/topic/kafka', (greeting) => {
        showKafka(JSON.parse(greeting.body).content);
    });

    stompClient.subscribe('/user/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    }, {header: "receipt-id"});

};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// Listen for the receipt event
// stompClient.onReceipt = (frame) => {
//     // Check the receipt id
//     if (frame.headers["receipt-id"] === "KAFKA-WEB") {
//       // Do something after the message is delivered
//       console.log("Message delivered successfully");
//     }
//   };

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/hello",
        body: JSON.stringify({ 'name': $("#name").val() }),
        headers: { receipt: "KAFKA-WEB" },
    });
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showKafka(message) {
    $("#greetings").append("<tr><td class='red-text'>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#send").click(() => sendName());
});