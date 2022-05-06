  function showOrHideLibro(tipoLibro) {
        if (gradeType === "fisico") {
              var x = document.getElementById("libroFisico");
              if (x.style.display === "none") {
                x.style.display = "block";
              } else {
                x.style.display = "none";
              }
        }
        if (gradeType === "kindle") {
             var x = document.getElementById("libroKindle");
             if (x.style.display === "none") {
                 x.style.display = "block";
            } else {
                x.style.display = "none";
            }
          }
        if (gradeType === "web") {
             var x = document.getElementById("libroWeb");
             if (x.style.display === "none") {
                 x.style.display = "block";
            } else {
                x.style.display = "none";
            }
          }
    }

    function deleteUsuario(id) {
    window.location.href = "/delete/usuario/" + id;
    }

    function deleteLibroFisico(id) {
    window.location.href = "/libros/" + id + "/" + "fisico";
    }

    function deleteLibroKindle(id) {
    window.location.href = "/libros/" + id + "/" + "kindle";
    }

    function deleteLibroWeb(id) {
    window.location.href = "/libros/" + id + "/" + "web";
    }

    function informacionUsuario(id) {
        window.location.href = "/informacionUsuario/" + id;
    }