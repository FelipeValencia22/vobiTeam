package com.vobi.team.presentation.backingBeans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class VtEmpresaView implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtEmpresaView.class);

	private InputText txtId;
	private InputText txtNombre;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public VtEmpresaView() {
		super();
	}

	public InputText getTxtId() {
		return txtId;
	}

	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	
	
	public String crearEmpresa(){
		log.info("Creando empresa");
		
		VtEmpresa vtEmpresa= new VtEmpresa();
		vtEmpresa.setNombre(txtNombre.getValue().toString().trim());
		vtEmpresa.setIdentificacion(txtId.getValue().toString().trim());
		vtEmpresa.setActivo("S");
		Date fechaCreacion = new Date();
		vtEmpresa.setFechaCreacion(fechaCreacion);
		VtUsuario vtUsuario =  (VtUsuario) FacesUtils.getfromSession("vtUsuario");
		vtEmpresa.setUsuCreador(vtUsuario.getUsuaCodigo());
		
		try {
			businessDelegatorView.saveVtEmpresa(vtEmpresa);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("La empresa se creó con exito"));
			limpiar();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
		return "";
	}
	
	public String modificarEmpresa(){
		log.info("Modificando empresa");
		
		VtEmpresa vtEmpresa = null;
		String identificacion= txtId.getValue().toString().trim();
		vtEmpresa=businessDelegatorView.consultarEmpresaPorId(identificacion);
		
		vtEmpresa.setIdentificacion(identificacion);
		vtEmpresa.setNombre(txtNombre.getValue().toString().trim());
		
		try {
			businessDelegatorView.updateVtEmpresa(vtEmpresa);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("La empresa se modifico con exito"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
		
		return "";
	}
	
	public String limpiar(){
		log.info("Limpiando campos de texto");
		txtNombre.resetValue();
		txtId.resetValue();

		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		return "";
	}
	
	
	public void txtIdListener(){
		log.info("Se ejecuto el listener");
		
		VtEmpresa vtEmpresa= null;
		
		String codigo= txtId.getValue().toString().trim();
		vtEmpresa=businessDelegatorView.consultarEmpresaPorId(codigo);

		
		if(vtEmpresa==null){
			txtNombre.resetValue();
			
			btnCrear.setDisabled(false);
			btnModificar.setDisabled(true);
		}else{
			
			txtNombre.setValue(vtEmpresa.getNombre());
			
			btnCrear.setDisabled(true);
			btnModificar.setDisabled(false);
		}
		
		
	}

	
}
