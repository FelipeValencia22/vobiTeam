package com.vobi.team.presentation.backingBeans;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.io.Serializable;
import java.sql.*;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtArchivoDTO;
import com.vobi.team.presentation.businessDelegate.*;
import com.vobi.team.utilities.*;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 * 
 */

@ManagedBean
@ViewScoped
public class VtArchivoView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(VtArchivoView.class);

	public VtArchivoView() {
		super();
	}

	private InputText txtActivo;
	private InputText txtArchivo;
	private InputText txtNombre;
	private InputText txtUsuCreador;
	private InputText txtUsuModificador;
	private InputText txtArteCodigo_VtArtefacto;
	private InputText txtArchCodigo;
	private Calendar txtFechaCreacion;
	private Calendar txtFechaModificacion;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<VtArchivoDTO> data;
	private VtArchivoDTO selectedVtArchivo;
	private VtArchivo entity;
	private boolean showDialog;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public void rowEventListener(RowEditEvent e) {
		try {

			VtArchivoDTO vtArchivoDTO = (VtArchivoDTO) e.getObject();

			if (txtActivo == null) {
				txtActivo = new InputText();
			}
			txtActivo.setValue(vtArchivoDTO.getActivo());
			if (txtArchivo == null) {
				txtArchivo = new InputText();
			}
			txtArchivo.setValue(vtArchivoDTO.getArchivo());
			if (txtNombre == null) {
				txtNombre = new InputText();
			}
			txtNombre.setValue(vtArchivoDTO.getNombre());
			if (txtUsuCreador == null) {
				txtUsuCreador = new InputText();
			}
			txtUsuCreador.setValue(vtArchivoDTO.getUsuCreador());
			if (txtUsuModificador == null) {
				txtUsuModificador = new InputText();
			}
			txtUsuModificador.setValue(vtArchivoDTO.getUsuModificador());
			if (txtArteCodigo_VtArtefacto == null) {
				txtArteCodigo_VtArtefacto = new InputText();
			}
			txtArteCodigo_VtArtefacto.setValue(vtArchivoDTO.getArteCodigo_VtArtefacto());
			if (txtArchCodigo == null) {
				txtArchCodigo = new InputText();
			}
			txtArchCodigo.setValue(vtArchivoDTO.getArchCodigo());
			if (txtFechaCreacion == null) {
				txtFechaCreacion = new Calendar();
			}
			txtFechaCreacion.setValue(vtArchivoDTO.getFechaCreacion());
			if (txtFechaModificacion == null) {
				txtFechaModificacion = new Calendar();
			}
			txtFechaModificacion.setValue(vtArchivoDTO.getFechaModificacion());

			Long archCodigo = FacesUtils.checkLong(txtArchCodigo);
			entity = businessDelegatorView.getVtArchivo(archCodigo);

			action_modify();

		} catch (Exception ex) {

		}

	}

	public String action_new() {
		action_clear();
		selectedVtArchivo = null;
		setShowDialog(true);
		return "";
	}

	public String action_clear() {

		entity = null;
		selectedVtArchivo = null;

		if (txtActivo != null) {
			txtActivo.setValue(null);
			txtActivo.setDisabled(true);
		}
		if (txtArchivo != null) {
			txtArchivo.setValue(null);
			txtArchivo.setDisabled(true);
		}
		if (txtNombre != null) {
			txtNombre.setValue(null);
			txtNombre.setDisabled(true);
		}
		if (txtUsuCreador != null) {
			txtUsuCreador.setValue(null);
			txtUsuCreador.setDisabled(true);
		}
		if (txtUsuModificador != null) {
			txtUsuModificador.setValue(null);
			txtUsuModificador.setDisabled(true);
		}
		if (txtArteCodigo_VtArtefacto != null) {
			txtArteCodigo_VtArtefacto.setValue(null);
			txtArteCodigo_VtArtefacto.setDisabled(true);
		}
		if (txtFechaCreacion != null) {
			txtFechaCreacion.setValue(null);
			txtFechaCreacion.setDisabled(true);
		}
		if (txtFechaModificacion != null) {
			txtFechaModificacion.setValue(null);
			txtFechaModificacion.setDisabled(true);
		}
		if (txtArchCodigo != null) {
			txtArchCodigo.setValue(null);
			txtArchCodigo.setDisabled(false);
		}
		if (btnSave != null) {
			btnSave.setDisabled(true);
		}
		if (btnDelete != null) {
			btnDelete.setDisabled(true);
		}
		return "";
	}

	public void listener_txtFechaCreacion() {
		Date inputDate = (Date) txtFechaCreacion.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtFechaModificacion() {
		Date inputDate = (Date) txtFechaModificacion.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtId() {

		try {
			Long archCodigo = FacesUtils.checkLong(txtArchCodigo);
			entity = archCodigo != null ? businessDelegatorView.getVtArchivo(archCodigo) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtActivo.setDisabled(false);
			txtArchivo.setDisabled(false);
			txtNombre.setDisabled(false);
			txtUsuCreador.setDisabled(false);
			txtUsuModificador.setDisabled(false);
			txtArteCodigo_VtArtefacto.setDisabled(false);
			txtFechaCreacion.setDisabled(false);
			txtFechaModificacion.setDisabled(false);
			txtArchCodigo.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtActivo.setValue(entity.getActivo());
			txtActivo.setDisabled(false);
			txtArchivo.setValue(entity.getArchivo());
			txtArchivo.setDisabled(false);
			txtFechaCreacion.setValue(entity.getFechaCreacion());
			txtFechaCreacion.setDisabled(false);
			txtFechaModificacion.setValue(entity.getFechaModificacion());
			txtFechaModificacion.setDisabled(false);
			txtNombre.setValue(entity.getNombre());
			txtNombre.setDisabled(false);
			txtUsuCreador.setValue(entity.getUsuCreador());
			txtUsuCreador.setDisabled(false);
			txtUsuModificador.setValue(entity.getUsuModificador());
			txtUsuModificador.setDisabled(false);
			txtArteCodigo_VtArtefacto.setValue(entity.getVtArtefacto().getArteCodigo());
			txtArteCodigo_VtArtefacto.setDisabled(false);
			txtArchCodigo.setValue(entity.getArchCodigo());
			txtArchCodigo.setDisabled(true);
			btnSave.setDisabled(false);
			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {

		selectedVtArchivo = (VtArchivoDTO) (evt.getComponent().getAttributes().get("selectedVtArchivo"));
		txtActivo.setValue(selectedVtArchivo.getActivo());
		txtActivo.setDisabled(false);
		txtArchivo.setValue(selectedVtArchivo.getArchivo());
		txtArchivo.setDisabled(false);
		txtFechaCreacion.setValue(selectedVtArchivo.getFechaCreacion());
		txtFechaCreacion.setDisabled(false);
		txtFechaModificacion.setValue(selectedVtArchivo.getFechaModificacion());
		txtFechaModificacion.setDisabled(false);
		txtNombre.setValue(selectedVtArchivo.getNombre());
		txtNombre.setDisabled(false);
		txtUsuCreador.setValue(selectedVtArchivo.getUsuCreador());
		txtUsuCreador.setDisabled(false);
		txtUsuModificador.setValue(selectedVtArchivo.getUsuModificador());
		txtUsuModificador.setDisabled(false);
		txtArteCodigo_VtArtefacto.setValue(selectedVtArchivo.getArteCodigo_VtArtefacto());
		txtArteCodigo_VtArtefacto.setDisabled(false);
		txtArchCodigo.setValue(selectedVtArchivo.getArchCodigo());
		txtArchCodigo.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if (selectedVtArchivo == null && entity == null) {
				action_create();
			} else {
				action_modify();
			}
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";

	}

	//TODO Implemnetar
	public String action_create() {
        try {
        	/*
        	entity = new VtArchivo();

	    	        Long archCodigo = FacesUtils.checkLong(txtArchCodigo);
	    
                        entity.setActivo(FacesUtils.checkString(txtActivo));
                                            	            		entity.setArchCodigo(archCodigo);
            	                                            entity.setArchivo(FacesUtils.check[B(txtArchivo));
                                entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
                                entity.setFechaModificacion(FacesUtils.checkDate(txtFechaModificacion));
                                entity.setNombre(FacesUtils.checkString(txtNombre));
                                            	            		entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            	                                                        	            		entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            	                                        entity.setVtArtefacto(FacesUtils.checkLong(txtArteCodigo_VtArtefacto) != null ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(txtArteCodigo_VtArtefacto)) : null );
        	        businessDelegatorView.saveVtArchivo(entity);
	        FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
			*/
        } catch (Exception e) {
        	entity = null;
        	FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }

	//TODO Implemnetar
	public String action_modify() {
		/*
        try {
        	if(entity==null){
		    		        Long archCodigo = new Long(selectedVtArchivo.getArchCodigo());
		    	    		entity = businessDelegatorView.getVtArchivo(archCodigo);
    		}
    		
        	    		entity.setActivo(FacesUtils.checkString(txtActivo));
    	        	        	    		entity.setArchivo(FacesUtils.check[B(txtArchivo));
    	        	    		entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
    	        	    		entity.setFechaModificacion(FacesUtils.checkDate(txtFechaModificacion));
    	        	    		entity.setNombre(FacesUtils.checkString(txtNombre));
    	        	    		entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
    	        	    		entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
    	    	    	        entity.setVtArtefacto(FacesUtils.checkLong(txtArteCodigo_VtArtefacto) != null ? businessDelegatorView.getVtArtefacto(FacesUtils.checkLong(txtArteCodigo_VtArtefacto)) : null );
	        	        businessDelegatorView.updateVtArchivo(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
		   data=null;
           FacesUtils.addErrorMessage(e.getMessage());
        }
        */
        return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedVtArchivo = (VtArchivoDTO) (evt.getComponent().getAttributes().get("selectedVtArchivo"));
			Long archCodigo = new Long(selectedVtArchivo.getArchCodigo());
			entity = businessDelegatorView.getVtArchivo(archCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_delete_master() {
		try {
			Long archCodigo = FacesUtils.checkLong(txtArchCodigo);
			entity = businessDelegatorView.getVtArchivo(archCodigo);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteVtArchivo(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			throw e;
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();
		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {

		try {
			selectedVtArchivo = (VtArchivoDTO) (evt.getComponent().getAttributes().get("selectedVtArchivo"));
			Long archCodigo = new Long(selectedVtArchivo.getArchCodigo());
			entity = businessDelegatorView.getVtArchivo(archCodigo);
			businessDelegatorView.deleteVtArchivo(entity);
			data.remove(selectedVtArchivo);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	//TODO Implementar
	/*
	public String action_modifyWitDTO(String activo, Long archCodigo, [B archivo, Date fechaCreacion, Date fechaModificacion, String nombre, Long usuCreador, Long usuModificador, Long arteCodigo_VtArtefacto) throws Exception {
        try {
        
        	    		entity.setActivo(FacesUtils.checkString(activo));
    	        	        	    		entity.setArchivo(FacesUtils.check[B(archivo));
    	        	    		entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
    	        	    		entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
    	        	    		entity.setNombre(FacesUtils.checkString(nombre));
    	        	    		entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
    	        	    		entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
    	            businessDelegatorView.updateVtArchivo(entity);
		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
        //renderManager.getOnDemandRenderer("VtArchivoView").requestRender();
        FacesUtils.addErrorMessage(e.getMessage());
        throw e;
        }
        return "";
        }
        */

	public InputText getTxtActivo() {
		return txtActivo;
	}

	public void setTxtActivo(InputText txtActivo) {
		this.txtActivo = txtActivo;
	}

	public InputText getTxtArchivo() {
		return txtArchivo;
	}

	public void setTxtArchivo(InputText txtArchivo) {
		this.txtArchivo = txtArchivo;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtUsuCreador() {
		return txtUsuCreador;
	}

	public void setTxtUsuCreador(InputText txtUsuCreador) {
		this.txtUsuCreador = txtUsuCreador;
	}

	public InputText getTxtUsuModificador() {
		return txtUsuModificador;
	}

	public void setTxtUsuModificador(InputText txtUsuModificador) {
		this.txtUsuModificador = txtUsuModificador;
	}

	public InputText getTxtArteCodigo_VtArtefacto() {
		return txtArteCodigo_VtArtefacto;
	}

	public void setTxtArteCodigo_VtArtefacto(InputText txtArteCodigo_VtArtefacto) {
		this.txtArteCodigo_VtArtefacto = txtArteCodigo_VtArtefacto;
	}

	public Calendar getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public Calendar getTxtFechaModificacion() {
		return txtFechaModificacion;
	}

	public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
		this.txtFechaModificacion = txtFechaModificacion;
	}

	public InputText getTxtArchCodigo() {
		return txtArchCodigo;
	}

	public void setTxtArchCodigo(InputText txtArchCodigo) {
		this.txtArchCodigo = txtArchCodigo;
	}

	public List<VtArchivoDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataVtArchivo();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public void setData(List<VtArchivoDTO> vtArchivoDTO) {
		this.data = vtArchivoDTO;
	}

	public VtArchivoDTO getSelectedVtArchivo() {
		return selectedVtArchivo;
	}

	public void setSelectedVtArchivo(VtArchivoDTO vtArchivo) {
		this.selectedVtArchivo = vtArchivo;
	}

	public CommandButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(CommandButton btnSave) {
		this.btnSave = btnSave;
	}

	public CommandButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(CommandButton btnModify) {
		this.btnModify = btnModify;
	}

	public CommandButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(CommandButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public CommandButton getBtnClear() {
		return btnClear;
	}

	public void setBtnClear(CommandButton btnClear) {
		this.btnClear = btnClear;
	}

	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

}
