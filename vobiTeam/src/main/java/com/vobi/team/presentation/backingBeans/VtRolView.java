package com.vobi.team.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;

import com.vobi.team.utilities.FacesUtils;
import com.vobi.team.modelo.dto.VtEmpresaDTO;
import com.vobi.team.modelo.dto.VtRolDTO;
import com.vobi.team.modelo.dto.VtUsuarioDTO;

@ManagedBean
@ViewScoped
public class VtRolView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtRolView.class);

	private InputText txtNombre;
	private SelectOneMenu somActivo;

	private List<SelectItem> esActivoItems;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	private CommandButton btnCrearU;
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	private List<VtRolDTO> data;
	private List<VtRolDTO> dataI;

	public List<VtRolDTO> getData() {
		 try {
	            if (data == null) {
	            	data = businessDelegatorView.getDataVtRol();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return data;
	}

	public void setData(List<VtRolDTO> data) {
		this.data = data;
	}

	public List<VtRolDTO> getDataI() {
		 try {
	            if (dataI == null) {
	            	dataI = businessDelegatorView.getDataVtRolInactivo();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return dataI;
	}

	public void setDataI(List<VtRolDTO> dataI) {
		this.dataI = dataI;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public VtRolView() {
		super();
	}

	public CommandButton getBtnCrearU() {
		return btnCrearU;
	}

	public void setBtnCrearU(CommandButton btnCrearU) {
		this.btnCrearU = btnCrearU;
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

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public List<SelectItem> getEsActivoItems() {
		if (esActivoItems == null) {
			esActivoItems = new ArrayList<SelectItem>();
			esActivoItems.add(new SelectItem("Si"));
			esActivoItems.add(new SelectItem("No"));

		}
		return esActivoItems;
	}

	public void setEsActivoItems(List<SelectItem> esActivoItems) {
		this.esActivoItems = esActivoItems;
	}

	public String crearRol() throws Exception {

		try {
			VtRol vtRol = new VtRol();
			vtRol.setRolNombre(txtNombre.getValue().toString().trim());
			Date fechaCreacion = new Date();
			vtRol.setFechaCreacion(fechaCreacion);
			VtUsuario vtUsuario = (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			vtRol.setUsuCreador(vtUsuario.getUsuaCodigo());
			String activo = somActivo.getValue().toString().trim();
			if (activo.equalsIgnoreCase("Si")) {
				vtRol.setActivo("S");
			} else {
				vtRol.setActivo("N");
			}
			businessDelegatorView.saveVtRol(vtRol);
			limpiar();
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El rol se creó con exito"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}

		return "";
	}

	 public String limpiar(){
	 log.info("Limpiando campos de texto");	 
	 txtNombre.resetValue();
	 somActivo.setValue("-1");	
	 btnCrear.setDisabled(false);
	 return "";
	 }
	//
	// public void txtLoginListener(){
	// log.info("Se ejecuto el listener");
	// VtUsuario vtUsuario= null;
	//
	// String login= txtLoginC.getValue().toString().trim();
	// vtUsuario=businessDelegatorView.consultarLogin(login);
	//
	// if(vtUsuario==null){
	// txtClave.resetValue();
	// txtClaveR.resetValue();
	// txtNombreC.resetValue();
	// somEmpresas.setValue("-1");
	//
	// btnCrearU.setDisabled(false);
	// }
	//
	// else{
	// txtNombreC.setValue(vtUsuario.getNombre());
	// somEmpresas.setValue(vtUsuario.getVtEmpresa().getEmprCodigo());
	//
	// btnCrearU.setDisabled(true);
	// }
	//
	// }
	//
	//
	/// Data Table
	private CommandButton btnSave;

	private VtUsuario entity;

	private VtUsuarioDTO selectedVtUsuario;

	private boolean showDialog;

	private InputText txtUsuaCodigo;

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public VtUsuario getEntity() {
		return entity;
	}

	public void setEntity(VtUsuario entity) {
		this.entity = entity;
	}

	public InputText getTxtUsuaCodigo() {
		return txtUsuaCodigo;
	}

	public void setTxtUsuaCodigo(InputText txtUsuaCodigo) {
		this.txtUsuaCodigo = txtUsuaCodigo;
	}

	public VtUsuarioDTO getSelectedVtUsuario() {
		return selectedVtUsuario;
	}

	public void setSelectedVtUsuario(VtUsuarioDTO selectedVtUsuario) {
		this.selectedVtUsuario = selectedVtUsuario;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

}
