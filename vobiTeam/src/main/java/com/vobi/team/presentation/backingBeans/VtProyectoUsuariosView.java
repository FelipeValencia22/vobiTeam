package com.vobi.team.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;

@ManagedBean 
@ViewScoped
public class VtProyectoUsuariosView implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtProyectoUsuariosView.class);

	private DualListModel<String> vtUsuario;
	private SelectOneMenu somProyectos;
	private List<SelectItem> losProyectosItems;
	private CommandButton btnCrear;
	private ValueChangeEvent somEmpresasEvent;
	private String proyectoSeleccionado;

	List<String> usuariosSource;
	List<String> usuariosTarget;

	private boolean showDialog;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public ValueChangeEvent getSomEmpresasEvent() {
		return somEmpresasEvent;
	}

	public void setSomEmpresasEvent(ValueChangeEvent somEmpresasEvent) {
		this.somEmpresasEvent = somEmpresasEvent;
	}

	public String getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}

	public void setProyectoSeleccionado(String proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}

	public List<String> getUsuariosSource() {
		return usuariosSource;
	}

	public void setUsuariosSource(List<String> usuariosSource) {
		this.usuariosSource = usuariosSource;
	}

	public List<String> getUsuariosTarget() {
		return usuariosTarget;
	}

	public void setUsuariosTarget(List<String> usuariosTarget) {
		this.usuariosTarget = usuariosTarget;
	}

	@PostConstruct
	public void init(){
		//Usuarios disponibles
		List<String> usuariosSource = new ArrayList<String>();
		//Usuarios no disponibles
		List<String> usuariosTarget = new ArrayList<String>();

		usuariosSource.add("Seleccionar proyecto");
		vtUsuario= new DualListModel<>(usuariosSource,usuariosTarget);
	}

	public String actionListarUsuarios() {
		usuariosSource = new ArrayList<String>(); //Usuarios disponibles
		usuariosTarget = new ArrayList<String>(); //Usuarios no disponibles

		if(getProyectoSeleccionado().equals("-1")){
			usuariosSource.add("Seleccionar proyecto");
		}else{ 
			try {
				List<VtUsuario> listUsuariosSource = businessDelegatorView.getVtUsuario();
				List<VtProyectoUsuario> listaProyectoUsuarios= businessDelegatorView.getVtProyectoUsuario();
				for(VtProyectoUsuario vtProyectoUsuario: listaProyectoUsuarios){
					if(vtProyectoUsuario.getVtProyecto().getNombre().equals(getProyectoSeleccionado())){
						for(VtUsuario vtUsuario: listUsuariosSource){
							if(vtProyectoUsuario.getVtUsuario().getUsuaCodigo() == (vtUsuario.getUsuaCodigo())){
								usuariosTarget.add("Nombre: "+vtUsuario.getNombre().toString()+", Login:"+ vtUsuario.getLogin()+"\n");
							}else{
								if(vtUsuario.getActivo().equalsIgnoreCase("S")){
									usuariosSource.add("Nombre: "+vtUsuario.getNombre().toString()+", Login:"+ vtUsuario.getLogin()+"\n");	
								}
							}
						}

					}else{
						for(VtUsuario vtUsuario: listUsuariosSource){
							if(vtUsuario.getActivo().equalsIgnoreCase("S")){
								usuariosSource.add("Nombre: "+vtUsuario.getNombre().toString()+", Login:"+ vtUsuario.getLogin()+"\n");	
							}
						}
						
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		vtUsuario = new DualListModel<String>(usuariosSource, usuariosTarget);

		return "";
	}

	public DualListModel<String> getVtUsuario() {
		return vtUsuario;
	}

	public void setVtUsuario(DualListModel<String> vtUsuario) {
		this.vtUsuario = vtUsuario;
	}

	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}

	public List<SelectItem> getLosProyectosItems() {
		try {
			if (losProyectosItems == null) {
				List<VtProyecto> listaProyectos = businessDelegatorView.getVtProyecto();
				losProyectosItems = new ArrayList<SelectItem>();
				for (VtProyecto vtProyecto : listaProyectos) {
					losProyectosItems.add(new SelectItem(vtProyecto.getNombre()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return losProyectosItems;
	}

	public void setLosProyectosItems(List<SelectItem> losProyectosItems) {
		this.losProyectosItems = losProyectosItems;
	}

	public void localeChanged(ValueChangeEvent e){
		setProyectoSeleccionado(e.getNewValue().toString());
		actionListarUsuarios();
		setShowDialog(true);
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public String action_closeDialog() {
		setShowDialog(false);
		usuariosSource = new ArrayList<String>(); //Usuarios disponibles
		usuariosTarget = new ArrayList<String>(); //Usuarios no disponibles
		vtUsuario = new DualListModel<String>(usuariosSource, usuariosTarget);
		return "";
	}

	public String action_Guardar() {

		// Source
		Object[] objectUsuariosSource= usuariosSource.toArray();
		String[] stringUsuariosSource= new String[objectUsuariosSource.length];

		for (int i = 0; i < objectUsuariosSource.length; i++) {
			stringUsuariosSource[i]=""+objectUsuariosSource[i];
		}

		String[] loginSource= new String[stringUsuariosSource.length];

		for (int i = 0; i < stringUsuariosSource.length; i++) {
			String[] parts = stringUsuariosSource[i].split("Login:");
			loginSource[i]=parts[1];
		}

		//Target
		Object[] objectUsuariosTarget= usuariosTarget.toArray();
		String[] stringUsuariosTarget= new String[objectUsuariosTarget.length];

		for (int i = 0; i < objectUsuariosTarget.length; i++) {
			stringUsuariosTarget[i]=""+objectUsuariosTarget[i];
		}

		String[] loginTarget= new String[stringUsuariosTarget.length];

		for (int i = 0; i < stringUsuariosTarget.length; i++) {
			String[] parts = stringUsuariosTarget[i].split("Login:");
			loginTarget[i]=parts[1];
		}

		
		try {
			VtProyecto vtProyecto = null;
			List<VtProyectoUsuario> listaProyectoUsuarios = businessDelegatorView.getVtProyectoUsuario();
			for(VtProyectoUsuario vtProyectoUsuario: listaProyectoUsuarios){
				if(vtProyectoUsuario.getVtProyecto().getNombre().equals(getProyectoSeleccionado())){
					vtProyecto = businessDelegatorView.getVtProyecto(vtProyectoUsuario.getVtProyecto().getProyCodigo());
					
					for(int i = 0;i < loginSource.length; i++){
						if(vtProyectoUsuario.getVtUsuario().getLogin().equals(loginSource[i])){
						}else{
							VtProyectoUsuario vtProyectoUsuarioCrear= new VtProyectoUsuario();
							vtProyectoUsuarioCrear.setVtProyecto(vtProyecto);

							Date fechaCreacion= new Date();
							vtProyectoUsuarioCrear.setFechaCreacion(fechaCreacion);

							VtUsuario vtUsuarioCreador =  (VtUsuario) FacesUtils.getfromSession("vtUsuario");
							vtProyectoUsuarioCrear.setUsuCreador(vtUsuarioCreador.getUsuaCodigo());

							VtUsuario vtUsuarioBuscar=businessDelegatorView.findUsuarioByLogin("pipeavb225@hotmail.com");
							vtProyectoUsuarioCrear.setVtUsuario(vtUsuarioBuscar);

							vtProyectoUsuarioCrear.setActivo("S");

							businessDelegatorView.saveVtProyectoUsuario(vtProyectoUsuarioCrear);
							FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Se realizo la asignacion del desarrollador(es)"));
						}
					}
					
				}
				
				
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}


		return "";
	}



}
