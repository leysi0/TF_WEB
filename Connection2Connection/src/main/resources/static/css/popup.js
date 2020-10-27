var btnAbrirpopup= document.getElementById('btn_fin'),
        overlay=document.getElementById('overlay'),
        popup=document.getElementById('popup');

btnAbrirpopup.addEventListener('click',function(){
    overlay.classList.add('active');
    popup.classList.add('active');
});