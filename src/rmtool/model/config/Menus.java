/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmtool.model.config;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import rmtool.controller.ProjetoFormController;
import rmtool.controller.RequisitoFormController;
import rmtool.model.TabManager;
import rmtool.model.TipoRequisito;
import rmtool.model.bean.Projeto;
import rmtool.model.bean.Requisito;
import rmtool.view.components.TextFieldTreeCellImpl;
import rmtool.view.components.TreeItemImpl;

/**
 *
 * @author jonimane
 */
public enum Menus {
    Root,
    Projeto,
    TipoRequisito,
    Requisito;
    
    private TextFieldTreeCellImpl pai;
    
    public List<MenuItem> gerarItens( TextFieldTreeCellImpl pai )
    {
        setPai(pai);
        
        switch( this )
        {
            case Root:
                return gerarRoot();
                
            case Projeto:
                return gerarProjeto();

            case Requisito:
                return gerarRequisito();
                
            case TipoRequisito:
                return gerarTipoRequisito();
            
            default:
                return null;
        }
    }
    
    public List<MenuItem> gerarRoot()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Adicionar Projeto", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TabManager.getInstance().criar( Telas.ProjetoForm );
            }
        }));
        
        return lista;
    }

    /**
     * Método responsável por gerar os menus para um Projeto
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarProjeto()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getObjeto( getTI() );
                
                TabManager tm = TabManager.getInstance();
                Tab t = tm.criar( Telas.ProjetoForm );
                ((ProjetoFormController) tm.get(t)).editar(p);
            }
        }));

        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Projeto p = getObjeto( getTI() );
            }
        }));

        lista.add( gerarMenuItem("Rastreabilidade", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        }));
        
        return lista;
    }
    
    /**
     * Método responsável por gerar os menus para um Requisito
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Editar", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = getObjeto( getTI() );
                TabManager tm = TabManager.getInstance();
                Tab t = tm.criar( Telas.RequisitoForm );
                ((RequisitoFormController) tm.get(t)).editar(r);
            }
        }));
        
        lista.add( gerarMenuItem("Excluir", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = getObjeto( getTI() );
            }
        }));
        
        return lista;
    }
    
    /**
     * Método responsável por gerar os menus para um TipoRequisito
     * 
     * @return List<MenuItem>
     */
    public List<MenuItem> gerarTipoRequisito()
    {
        List<MenuItem> lista = new ArrayList<>();
        
        lista.add( gerarMenuItem("Criar Requisito", new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Requisito r = new Requisito();
                r.setTipo( (TipoRequisito) getObjeto( getTI() ) );
                r.setProjeto( (Projeto) getObjeto( getTI().getParentTI() ) );
                
                TabManager tm = TabManager.getInstance();
                Tab t = tm.criar( Telas.RequisitoForm );
                ((RequisitoFormController) tm.get(t)).editar(r);
            }
        }));
        
        return lista;
    }

    /**
     * Método responsável para retornar qual a TreeCell em que está acontecendo o evento
     * 
     * @return TextFieldTreeCellImpl
     */
    public TextFieldTreeCellImpl getPai() {
        return pai;
    }

    public void setPai(TextFieldTreeCellImpl pai) {
        this.pai = pai;
    }
    
    public TreeItemImpl getTI()
    {
        return ( TreeItemImpl ) pai.getTreeItem();
    }
    
    public <T> T getObjeto( TreeItemImpl ti )
    {
        return (T) ti.getObjeto();
    }
    
    public MenuItem gerarMenuItem( String titulo, EventHandler<ActionEvent> acao)
    {
        MenuItem mi = new MenuItem();
        mi.setText(titulo);
        mi.setOnAction( acao );
        
        return mi;
    }
}
