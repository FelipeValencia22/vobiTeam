package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtRolDTO;
import com.vobi.team.presentation.businessDelegate.*;
import com.vobi.team.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class VtRolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtRolView.class);
    private InputText txtActivo;
    private InputText txtRolNombre;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtRolCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtRolDTO> data;
    private VtRolDTO selectedVtRol;
    private VtRol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtRolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtRolDTO vtRolDTO = (VtRolDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtRolDTO.getActivo());

            if (txtRolNombre == null) {
                txtRolNombre = new InputText();
            }

            txtRolNombre.setValue(vtRolDTO.getRolNombre());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtRolDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtRolDTO.getUsuModificador());

            if (txtRolCodigo == null) {
                txtRolCodigo = new InputText();
            }

            txtRolCodigo.setValue(vtRolDTO.getRolCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtRolDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtRolDTO.getFechaModificacion());

            Long rolCodigo = FacesUtils.checkLong(txtRolCodigo);
            entity = businessDelegatorView.getVtRol(rolCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtRol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtRol = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtRolNombre != null) {
            txtRolNombre.setValue(null);
            txtRolNombre.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtRolCodigo != null) {
            txtRolCodigo.setValue(null);
            txtRolCodigo.setDisabled(false);
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
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaModificacion() {
        Date inputDate = (Date) txtFechaModificacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long rolCodigo = FacesUtils.checkLong(txtRolCodigo);
            entity = (rolCodigo != null)
                ? businessDelegatorView.getVtRol(rolCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtRolNombre.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtRolCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtRolNombre.setValue(entity.getRolNombre());
            txtRolNombre.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtRolCodigo.setValue(entity.getRolCodigo());
            txtRolCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtRol = (VtRolDTO) (evt.getComponent().getAttributes()
                                       .get("selectedVtRol"));
        txtActivo.setValue(selectedVtRol.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtRol.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtRol.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtRolNombre.setValue(selectedVtRol.getRolNombre());
        txtRolNombre.setDisabled(false);
        txtUsuCreador.setValue(selectedVtRol.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtRol.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtRolCodigo.setValue(selectedVtRol.getRolCodigo());
        txtRolCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtRol == null) && (entity == null)) {
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

    public String action_create() {
        try {
            entity = new VtRol();

            Long rolCodigo = FacesUtils.checkLong(txtRolCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setRolCodigo(rolCodigo);
            entity.setRolNombre(FacesUtils.checkString(txtRolNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.saveVtRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long rolCodigo = new Long(selectedVtRol.getRolCodigo());
                entity = businessDelegatorView.getVtRol(rolCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setRolNombre(FacesUtils.checkString(txtRolNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.updateVtRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtRol = (VtRolDTO) (evt.getComponent().getAttributes()
                                           .get("selectedVtRol"));

            Long rolCodigo = new Long(selectedVtRol.getRolCodigo());
            entity = businessDelegatorView.getVtRol(rolCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long rolCodigo = FacesUtils.checkLong(txtRolCodigo);
            entity = businessDelegatorView.getVtRol(rolCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtRol(entity);
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
            selectedVtRol = (VtRolDTO) (evt.getComponent().getAttributes()
                                           .get("selectedVtRol"));

            Long rolCodigo = new Long(selectedVtRol.getRolCodigo());
            entity = businessDelegatorView.getVtRol(rolCodigo);
            businessDelegatorView.deleteVtRol(entity);
            data.remove(selectedVtRol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, Long rolCodigo, String rolNombre,
        Long usuCreador, Long usuModificador) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setRolNombre(FacesUtils.checkString(rolNombre));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtRol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtRolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtActivo() {
        return txtActivo;
    }

    public void setTxtActivo(InputText txtActivo) {
        this.txtActivo = txtActivo;
    }

    public InputText getTxtRolNombre() {
        return txtRolNombre;
    }

    public void setTxtRolNombre(InputText txtRolNombre) {
        this.txtRolNombre = txtRolNombre;
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

    public InputText getTxtRolCodigo() {
        return txtRolCodigo;
    }

    public void setTxtRolCodigo(InputText txtRolCodigo) {
        this.txtRolCodigo = txtRolCodigo;
    }

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

    public void setData(List<VtRolDTO> vtRolDTO) {
        this.data = vtRolDTO;
    }

    public VtRolDTO getSelectedVtRol() {
        return selectedVtRol;
    }

    public void setSelectedVtRol(VtRolDTO vtRol) {
        this.selectedVtRol = vtRol;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
