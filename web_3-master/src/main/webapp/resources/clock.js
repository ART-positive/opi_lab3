const canvas = document.getElementById("canvas");
var context = canvas.getContext('2d');
var radius = canvas.height/2;
context.translate(radius, radius);
radius = radius*0.9
drawFace(context, radius);
drawNotches(context, radius);
drawHands(context, radius);
setInterval(drawClock, 12000);
function drawClock(){
    context.clearRect(-canvas.height/2,canvas.height/2,canvas.height,-canvas.height)
    drawFace(context,radius);
    drawNotches(context,radius);
    drawHands(context,radius);
}
function drawFace(context, radius){
    context.strokeStyle='black'
    context.lineWidth=5;
    context.beginPath();
    context.arc(0, 0, radius, 0, 2 * Math.PI);
    context.fillStyle = 'white';
    context.fill();
    context.beginPath();
    context.arc(0, 0, radius, 0, 2 * Math.PI);
    context.stroke();
    context.beginPath();
    context.arc(0, 0, radius * 0.07, 0, 2 * Math.PI);
    context.fillStyle = 'black';
    context.fill();
}
function drawNotches(context, radius){
    let angle = Math.PI/4
    context.lineWidth=4;
    context.strokeStyle='black';
    for (let i=0; i<8;i++){
        let startX = 0;
        let startY = radius;
        let endX = 0;
        let endY= radius*0.9;
        context.beginPath();
        context.moveTo(startX, startY);
        context.lineTo(endX, endY);
        context.stroke()
        context.rotate(angle)
    }
}
function drawHand(context, pos, length, width) {
    context.beginPath();
    context.lineWidth = width;
    context.lineCap = "round";
    context.moveTo(0, 0);
    context.rotate(pos);
    context.lineTo(0, -length);
    context.stroke();
    context.rotate(-pos);
}
function drawHands(context, radius){
    const now = new Date();
    let minute = now.getMinutes();
    let second = now.getSeconds();
    let hour = now.getHours();
    let newSecond = (second * Math.PI / 30)
    drawHand(context, newSecond, radius * 0.7, radius * 0.03);
    let newMinute = (minute * Math.PI / 30) + (second * Math.PI / (30 * 60)); // bc deg to radians = deg*pi/180
    drawHand(context, newMinute, radius*0.6, radius*0.05);
    let newHour = (hour*Math.PI/6)+(minute*Math.PI/(6*60))+(second*Math.PI/(360*60)); //??? why these numbers
    drawHand(context, newHour, radius*0.5, radius*0.07);
}