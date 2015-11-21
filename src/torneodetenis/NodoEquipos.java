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
public class NodoEquipos implements Serializable
{
    private String universidad;
    private int puntos;
    private int ranking;
    private NodoEquipos proximo;
    private ListaCategorias categorias;

    public NodoEquipos(String universidad, int puntos, int ranking)
    {
        this.universidad = universidad;
        this.puntos = puntos;
        this.ranking = ranking;
        this.proximo = null;
        this.categorias = new ListaCategorias(null);
    }

    public String getUniversidad()
    {
        return universidad;
    }

    public void setUniversidad(String universidad) 
    {
        this.universidad = universidad;
    }

    public int getPuntos() 
    {
        return puntos;
    }

    public void setPuntos(int puntos) 
    {
        this.puntos = puntos;
    }

    public ListaCategorias getCategorias()
    {
        return categorias;
    }

    public int getRanking()
    {
        return ranking;
    }

    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    public NodoEquipos getProximo()
    {
        return proximo;
    }

    public void setProximo(NodoEquipos proximo)
    {
        this.proximo = proximo;
    }
    
    public void mostrar()
    {
        System.out.println("Universidad: " + universidad + "- Puntos: " + puntos);
    }
    
    public void sortCategoria(NodoJugadores jugador) throws Exception
    {
        NodoCategorias nodCategoria = new NodoCategorias(jugador.getCategoria(), jugador.getSexo());
        NodoCategorias aux = null;
        
        if(categorias.estaVacia())
        {
            categorias.insertarPrimero(nodCategoria);
            System.out.println("Categoria nueva: " + nodCategoria.getNumCategoria() + " - " + nodCategoria.getTipoCategoria());
            nodCategoria.insertarABB(jugador);
        }
        else
        {
            aux = categorias.buscarCategorias(nodCategoria);
            
            if(aux != null)
            {
                System.out.println("Categoria registrada: " + aux.getNumCategoria() + " - " + aux.getTipoCategoria());
                aux.insertarABB(jugador);
            }
            else
            {
                categorias.insertarPrimero(nodCategoria);
                System.out.println("Categoria nueva: " + nodCategoria.getNumCategoria() + " - " + nodCategoria.getTipoCategoria());
                nodCategoria.insertarABB(jugador);
            }
        }
    }
    
    static final long serialVersionUID = 8925409;
}
