$(document).ready(()=>{
    var myModal = new bootstrap.Modal(document.getElementById('myModal'));
    $('.edit').on('click', (event)=>{
        myModal.show();
        let target = $(event.target);
        let id = target.data('id');
        $.ajax({    
            url : '/api/Admin/' + id,
            dataType : 'json',
            type : 'GET',
            dataType : 'json',
            success : function(json) {
                if(json.items.length > 0){
                    $("#id_admin").val(json.items[0].id);
                    $("#nombre_admin").val(json.items[0].nombre);
                    $("#email_admin").val(json.items[0].email);
                    $("#psswd_admin").val(json.items[0].password);
                }
                else{
                    alert("Administrador con ID "+ id +" no existe.");
                }
            },
            error : function(xhr, status) {
                alert('Ha sucedido un problema'+ xhr.status);
            }
        });
    });
});


