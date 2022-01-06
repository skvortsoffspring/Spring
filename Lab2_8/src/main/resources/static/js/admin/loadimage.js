let fileReader = new FileReader();
let resultToImageFiles ;
let image = document.getElementById("image");

document.getElementById('file')
    .addEventListener('change', function() {


        fileReader.onload=function(){
            resultToImageFiles=btoa(String.fromCharCode.apply(null, new Uint8Array(fileReader.result)));
            image.src = `data:image/jpg;base64,${resultToImageFiles}`;
            console.log(resultToImageFiles);
        }

        fileReader.readAsArrayBuffer(this.files[0]);
    })