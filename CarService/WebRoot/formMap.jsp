<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>折线、多边形、圆</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.4.4&key=您申请的key值"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<script>
    var map = new AMap.Map('container', {
        resizeEnable: true,
        center: [115.802487,28.659081],
        zoom: 13
    });
    var lineArr = [
        [115.799225,28.646879],
        [115.803173,28.653432],
        [115.811585,28.657574],
        [115.818537,28.659533],
         [115.82712,28.66488],
          [115.836604,28.662658],
           [115.840552,28.661227],
            [115.842698,28.664842],
             [115.843986,28.667403],
              [115.845702,28.668721]
              
         
         
        
        
    ];
    var polyline = new AMap.Polyline({
        path: lineArr,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 5,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    polyline.setMap(map);




</script>
</body>
</html>