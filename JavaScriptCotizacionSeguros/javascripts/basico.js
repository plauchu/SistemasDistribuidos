function actualiza(id_credencial){
    var saludoFormal= "Sr. ";
    if (document.forms["cliente"]["genero"].value=="femenino"){
        saludoFormal= "Sra. ";
    }
        
    document.getElementById(id_credencial).innerHTML = 
        saludoFormal +
        document.forms["cliente"]["nombre"].value + " " +
        document.forms["cliente"]["apellidos"].value;
}