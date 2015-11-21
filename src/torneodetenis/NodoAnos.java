/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class NodoAnos implements Serializable
{
    private int ano;
    private NodoAnos proximo;
    private ListaEquipos equipos;

    public NodoAnos(int año) 
    {
        this.ano = año;
        this.proximo = null;
        this.equipos = new ListaEquipos();
    }

    public int getAno() 
    {
        return ano;
    }

    public void setAno(int año) 
    {
        this.ano = año;
    }

    public ListaEquipos getEquipos()
    {
        return equipos;
    }

    public NodoAnos getProximo() 
    {
        return proximo;
    }

    public void setProximo(NodoAnos proximo) 
    {
        this.proximo = proximo;
    }
    
    public void mostrar()
    {
        System.out.println("Año: " + ano);
    }
    
    public void sortEquipo(NodoJugadores jugador) throws Exception
    {
        NodoEquipos nodEquipo = new NodoEquipos(jugador.getEquipo(), 0, 0);
        NodoEquipos aux = null;
        
        if(equipos.estaVacia())
        {
            equipos.insertarPrimero(nodEquipo);
            System.out.println("Equipo nuevo: " + nodEquipo.getUniversidad());
            nodEquipo.sortCategoria(jugador);
        }
        else
        {
            aux = equipos.buscarEquipos(nodEquipo);
            
            if(aux != null)
            {
                System.out.println("Equipo registrado: " + aux.getUniversidad());
                aux.sortCategoria(jugador);
            }
            else
            {
                equipos.insertarPrimero(nodEquipo);
                System.out.println("Equipo nuevo: " + nodEquipo.getUniversidad());
                nodEquipo.sortCategoria(jugador);
            }
        }
    }
    
    static final long serialVersionUID = 8925409;
}
