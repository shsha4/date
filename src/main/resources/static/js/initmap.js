let lat;
let lon;
let map;
let ps;
let marker;
let markerImage = new kakao.maps.MarkerImage('images/marker.png', new kakao.maps.Size(24, 24));
let places;

function initMap(latitude, longitude) {
    if(!latitude && !longitude) {
        if (!!navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
        }

        function errorCallback(err) {
            alert(err + '\n이 브라우저는 Geolocation을 지원하지 않습니다');
            return location.href = 'index';
        }

        function successCallback(position) {
            lat = !!lat ? lat : position.coords.latitude;
            lon = !!lon ? lon : position.coords.longitude;
            mapBuilder(lat, lon);
        }
    } else {
        mapBuilder(latitude, longitude);
    }
}

function mapBuilder(lat, lon) {
    let mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(lat, lon)
        };
    map = new kakao.maps.Map(mapContainer, mapOption);
    ps = new kakao.maps.services.Places();
}