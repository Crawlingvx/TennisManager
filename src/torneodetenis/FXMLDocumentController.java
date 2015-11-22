/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;

/**
 * Clase FXMLDocumentController
 * Esta clase es el controlador de la interfaz. Aqui es donde se manejan todos los atributos y metodos a usar con la interfaz grafica.
 * Los atributos y metodos pueden ser generados manualmente, o generados directamente desde Scene Builder.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 6.02, 20/11/2015
 */
public class FXMLDocumentController implements Initializable 
{
    
    private ColaJugadores queue; //Representa la cola de jugadores a ser vaciada en el torneo
    private ListaAnos anos; //Representa la lista de anos a ser guardada/recuperada que contiene toda la informacion
    private NodoAnos anoImplementado; //Año en el que se esta consultando/modificando la informacion
    private String equipoElegido; //representa el equipo a implementar en las busquedas para Historial de Equipos y Jugadores
    
    //Panel de "Crear Torneo"
    @FXML
    private Button clickCrear;
    @FXML
    private Button crearTorneo;
    @FXML
    private TextField textEdad;
    @FXML
    private TextField textCedula;
    @FXML
    private TextField textSexo;
    @FXML
    private TextField textEquipo;
    @FXML
    private Button botonAñadir;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCategoria;
    
    
    private TabPane clickConsultar;
    @FXML
    private Button clickEncuentros;
    @FXML
    private GridPane grid3;
    @FXML
    private GridPane grid4;
    @FXML
    private Button eliminarCat3;
    @FXML
    private Button eliminarCat4;
    @FXML
    private Button eliminarCat5;
    @FXML
    private Button eliminarCat6;
    @FXML
    private Button eliminarEquipo;
    @FXML
    private Button salvarCambios;
    @FXML
    private TextField textPuntos;
    @FXML
    private TextField textRanking;
    @FXML
    private TextField textAño;
    @FXML
    private Button clickModificar;
    @FXML
    private TabPane paneConsultar;
    @FXML
    private Pane paneEncuentros;
    @FXML
    private Pane paneCrear;
    @FXML
    private Button botonJugador;
    @FXML
    private ComboBox<NodoEquipos> comboEquipo;
    @FXML
    private GridPane grid5;
    @FXML
    private GridPane grid6;
    
    /**
    * Metodo initialize
    * Metodo que inicializa diversos atributos/variables al lanzar el programa. Tambien es capaz de ejecutar metodos.
    * 
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        queue = new ColaJugadores(null);
        anos = new ListaAnos();
    }    
    
    /**
    * Metodo clickCrear
    * Metodo que hace visible la pantalla de crear el torneo, e invisible las pantallas de consultar/modificar informacion y 
    * encuentros.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Crear Torneo)
    */
    @FXML
    private void clickCrear(ActionEvent event) 
    {
        paneCrear.setVisible(true);
        paneConsultar.setVisible(false);
        paneEncuentros.setVisible(false);
    }
    
    /**
    * Metodo clickConsultar
    * Metodo que hace visible la pantalla de consultar/modificar la informacion, e invisible las pantallas de crear el torneo y 
    * encuentros. Tambien, crear un popup para elegir el ano a consultar.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Consultar/modificar informacion)
    */
    @FXML
    private void clickConsultar(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        elegirAno();
        paneConsultar.setVisible(true);
        paneCrear.setVisible(false);
        paneEncuentros.setVisible(false);
    }
    
    /**
    * Metodo clickEncuentros
    * Metodo que hace visible la pantalla de encuentros, e invisible las pantallas de crear el torneo y consultar/modificar la
    * informacion.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Encuentros)
    */
    @FXML
    private void clickEncuentros(ActionEvent event) 
    {
        paneEncuentros.setVisible(true);
        paneConsultar.setVisible(false);
        paneCrear.setVisible(false);
    }
    
    /**
    * Metodo elegirAno
    * Metodo para elegir el ano a consultar.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Encuentros)
    */
    private void elegirAno() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        int ano = elegirAnoPopup();
        NodoAnos nodAno = new NodoAnos(ano); //crea un nodo NodoAnos con el ano a consultar para buscar su equivalente en la lista de anos
        
        anoImplementado = anos.buscarAnos(nodAno); //consigue el equivalente de nodAno y lo guarda en el atributo anoImplementado
        
        recuperarInformacionEquipo();
    }
    
    /**
    * Metodo elegirAnoPopup
    * Metodo que despliega un popup con un DialogChoice que contiene los anos donde se han creado torneos. Estos son recuperados
    * del archivo de objetos, como una ListaAnos que contiene toda la informacion del programa.
    * 
    */
    private int elegirAnoPopup() throws IOException, FileNotFoundException, ClassNotFoundException
    {
        NodoAnos aux = new NodoAnos(1); //instancia un nodoAnos con un numero de ano '1' para que entre en el ciclo while.
        List<String> choices = new ArrayList<>(); //crea un Arraylist para recuperar la informacion de los anos donde existen torneos
        
        anos = anos.leerArchivo(anos); //recupera la ListaAnos del archivo de objetos y la asigna al atributo anos.
        
        while(aux != null)
        {
            aux = anos.llenarDialogo(aux); //devuelve los NodoAnos con anos donde se crearon los torneos.
            
            if(aux != null)
                choices.add(Integer.toString(aux.getAno())); //anade einformacion al arrayList que contiene los anos donde se crearon torneos como un String
        }
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Elegir año",choices); //instancia un ChoiceDialog con los anos que se han hecho torneos
        dialog.setTitle("Consultar Informacion");
        dialog.setHeaderText("Por favor, introduzca la informacion requerida");
        dialog.setContentText("Introduzca el año a consultar:");

        Optional<String> result = dialog.showAndWait(); //deja el popup en pantalla hasta que se elija una opcion
        return Integer.parseInt(result.get()); //retorna el resultado elegido como un entero
    }

    /**
    * Metodo llenarCola
    * llena la cola de jugadores a traves de la informacion introducida por el usuario.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Anadir)
    */
    @FXML
    private void llenarCola(ActionEvent event) 
    {
        //convierte alguno de los Strings introducidos en los TextFields en enteros.
        int cedula = Integer.parseInt(textCedula.getText());
        int edad = Integer.parseInt(textEdad.getText());
        int categoria = Integer.parseInt(textCategoria.getText());
        
        //condicion para asegurar que los TextFields de sexo no sean diferentes a 'm' o 'f', y que la categoria introducida este entre 3 y 6
        if( ((!textSexo.getText().equalsIgnoreCase("m")) && (!textSexo.getText().equalsIgnoreCase("f"))) ||  (categoria < 3) || (categoria > 6) )
        {
            popupEntrada(false); //muestra un popup de error
        }
        else
        {
            popupEntrada(true); //muestra un popup de exito al introducir la informacion correctamente
            
            //crea un jugador con la informacion introducida por el usuario
            NodoJugadores jugador = new NodoJugadores(textNombre.getText(), cedula, textSexo.getText(), edad, textEquipo.getText(), categoria, 0, null);
            queue.encolar(jugador); //inserta al jugador en la cola
        }
        
        clearText(); //limpia todos los TextFields
        
    }
    
    /**
    * Metodo popupEntrada
    * Genera un popup Alert de error o de exito, dependiendo de la informacion que el usuario haya introducido referente a los
    * jugadores que se inscriben en el torneo
    * 
    * @param bool indica si debe aparecer el popup de error o de exito
    */
    private void popupEntrada(boolean bool)
    {
        if(bool == false)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error de Entrada");
            alert.setContentText("Ha introducido informacion invalida. Por favor, intentelo de nuevo.");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Exito!");
            alert.setHeaderText(null);
            alert.setContentText("Ha introducido al jugador exitosamente.");
            alert.showAndWait();
        }
    }
    
    /**
    * Metodo clearText
    * Metodo que limpia todos los TextFields una vez se haya introducido la informacion del jugador y presionado el boton de anadir
    * 
    */
    private void clearText()
    {
        textNombre.clear();
        textCedula.clear();
        textSexo.clear();
        textEdad.clear();
        textEquipo.clear();
        textCategoria.clear();
    }
    
    /**
    * Metodo llenarTorneo
    * Metodo que crea el torneo una vez se haya terminado de introducir los jugadores que participaran
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Crear Torneo)
    */
    @FXML
    private void llenarTorneo(ActionEvent event) throws IOException, ClassNotFoundException, Exception 
    {
        int numAno = Integer.parseInt(textAño.getText());
        NodoJugadores jugador; //representa el jugador a ser desencolado
        NodoAnos nodAno = new NodoAnos(numAno); //crea un NodoAnos con el ano a ser introducido a la lista
        NodoAnos aux; //NodoAnos retornado si el ano en el que se quiere crear el torneo ya existe.
        
        anos = anos.leerArchivo(anos); //lee el archivo de objetos que retorna la lista que contiene la informacion de todos los torneos
        aux = anos.buscarAnos(nodAno); //busca que el ano en el que se trata de crear el torneo no exista
        
        if(aux != null)
        {
            System.out.println("Este año ya existe");
        }
        else
        {
            anos.insertarPrimero(nodAno); //inserta el ano en el que se va a crear el torneo
            
            while(queue.getCabeza() != null) //ciclo para desencolar e introducir a todos los jugadores en el torneo
            {
                jugador = queue.desencolar(); //desencola al jugador
                nodAno.sortEquipo(jugador); //envia al jugador a validar su informacion en la lista de equipos
            }
        }
        
        anos.crearArchivo(anos); //Anade al archivo de objetos la nueva lista de anos con la informacion recien anadida
    }

    /**
    * Metodo recuperarInformacionEquipo
    * Metodo que recupera la informacion del equipo elegido a visualizar en el Historial de Equipos.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Elegir Equipo)
    */
    private void recuperarInformacionEquipo()
    {
        ListaEquipos listaEquipo = new ListaEquipos(); //lista donde se buscara el equipo elegido
        
        listaEquipo = anoImplementado.getEquipos(); //llena lista de equipos, con los equipos que jugaron el ano elegido (e.g: 2013)
        
        llenarComboBoxEquipo(listaEquipo); //llena el ComboBox de los equipos que participaron este año
        
    }
    
    /**
    * Metodo popupElegirEquipo
    * Metodo que despliega un ChoiceDialog para elegir la informacion del equipo que sera consultado/modificado
    * 
    * @param listaEquipo la lista de equipos del ano a consultar
    */
    private void llenarComboBoxEquipo(ListaEquipos listaEquipo)
    {
        NodoEquipos aux;
        ObservableList<NodoEquipos> ol = FXCollections.observableArrayList();
        aux = listaEquipo.getCabeza();
        
        while(aux != null)
        {
            ol.add(aux);
            aux = aux.getProximo();
        }
        comboEquipo.setItems(ol);
        
        // Define rendering of the list of values in ComboBox drop down. 
        comboEquipo.setCellFactory((comboBox) -> 
        {
            return new ListCell<NodoEquipos>() 
            {
                @Override
                protected void updateItem(NodoEquipos item, boolean empty) 
                {
                    super.updateItem(item, empty);

                    if (item == null || empty) 
                    {
                        setText(null);
                    } 
                    else 
                    {
                        setText(item.getUniversidad());
                    }
                }
            };
        });

        // Define rendering of selected value shown in ComboBox.
        comboEquipo.setConverter(new StringConverter<NodoEquipos>() 
        {
            @Override
            public String toString(NodoEquipos nodEquipo) 
            {
                    return nodEquipo.getUniversidad();
            }

            @Override
            public NodoEquipos fromString(String personString) 
            {
                return null; // No conversion fromString needed.
            }
        });
    }
    
    @FXML
    private void eleccionComboEquipos(ActionEvent event)
    {
        NodoEquipos equipoElegido = comboEquipo.getSelectionModel().getSelectedItem();
        NodoCategorias categoria = equipoElegido.getCategorias().getCabeza();
        
        ABBJugadores arbol = categoria.getJugadores();
        
        while(categoria != null)
        {
            NodoJugadores jugador = categoria.getJugadores().getRaiz();
            
            if(jugador.getCategoria() == 3)
                arbol.retornarEnOrden(jugador, grid3);
            else if(jugador.getCategoria() == 4)
                arbol.retornarEnOrden(jugador, grid4);
            else if(jugador.getCategoria() == 5)
                arbol.retornarEnOrden(jugador, grid5);
            else if(jugador.getCategoria() == 6)
                arbol.retornarEnOrden(jugador, grid6);
            
            categoria = categoria.getProximo();
        }
    }

    /**
    * Metodo salvarCambios
    * Metodo que Salva los cambios realizados en el historial de equipos.
    * 
    * @param event Significa que este metodo es llamado con el click de un boton (Salvar Cambios)
    */
    @FXML
    private void salvarCambios(ActionEvent event) //NO SIRVE
    {
        
    }
}
