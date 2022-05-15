  function showOrHideLibro(tipoLibro) {
        if (tipoLibro === "Físico") {
              var x = document.getElementById("libroFisico");
              if (x.style.display === "none") {
                x.style.display = "block";
              } else {
                x.style.display = "none";
              }
        }
        if (tipoLibro === "Kindle") {
             var x = document.getElementById("libroKindle");
             if (x.style.display === "none") {
                 x.style.display = "block";
            } else {
                x.style.display = "none";
            }
          }
        if (tipoLibro === "Web") {
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
    window.location.href = "/libros/" + id + "/" + "Físico";
    }

    function deleteLibroKindle(id) {
    window.location.href = "/libros/" + id + "/" + "Kindle";
    }

    function deleteLibroWeb(id) {
    window.location.href = "/libros/" + id + "/" + "Web";
    }

    function informacionUsuario(id) {
        window.location.href = "/informacionUsuario/" + id;
    }