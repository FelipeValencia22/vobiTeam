package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtPrioridadDTO;
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
public class VtPrioridadView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtPrioridadView.class);
    private InputText txtActivo;
    private InputText txtNombre;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtPrioCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtPrioridadDTO> data;
    private VtPrioridadDTO selectedVtPrioridad;
    private VtPrioridad entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtPrioridadView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtPrioridadDTO vtPrioridadDTO = (VtPrioridadDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtPrioridadDTO.getActivo());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtPrioridadDTO.getNombre());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtPrioridadDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtPrioridadDTO.getUsuModificador());

            if (txtPrioCodigo == null) {
                txtPrioCodigo = new InputText();
            }

            txtPrioCodigo.setValue(vtPrioridadDTO.getPrioCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtPrioridadDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtPrioridadDTO.getFechaModificacion());

            Long prioCodigo = FacesUtils.checkLong(txtPrioCodigo);
            entity = businessDelegatorView.getVtPrioridad(prioCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtPrioridad = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtPrioridad = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
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

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtPrioCodigo != null) {
            txtPrioCodigo.setValue(null);
            txtPrioCodigo.setDisabled(false);
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
            Long prioCodigo = FacesUtils.checkLong(txtPrioCodigo);
            entity = (prioCodigo != null)
                ? businessDelegatorView.getVtPrioridad(prioCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtNombre.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtPrioCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
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
            txtPrioCodigo.setValue(entity.getPrioCodigo());
            txtPrioCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtPrioridad = (VtPrioridadDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtPrioridad"));
        txtActivo.setValue(selectedVtPrioridad.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtPrioridad.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtPrioridad.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedVtPrioridad.getNombre());
        txtNombre.setDisabled(false);
        txtUsuCreador.setValue(selectedVtPrioridad.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtPrioridad.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtPrioCodigo.setValue(selectedVtPrioridad.getPrioCodigo());
        txtPrioCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtPrioridad == null) && (entity == null)) {
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
            entity = new VtPrioridad();

            Long prioCodigo = FacesUtils.checkLong(txtPrioCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setPrioCodigo(prioCodigo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.saveVtPrioridad(entity);
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
                Long prioCodigo = new Long(selectedVtPrioridad.getPrioCodigo());
                entity = businessDelegatorView.getVtPrioridad(prioCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            businessDelegatorView.updateVtPrioridad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtPrioridad = (VtPrioridadDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedVtPrioridad"));

            Long prioCodigo = new Long(selectedVtPrioridad.getPrioCodigo());
            entity = businessDelegatorView.getVtPrioridad(prioCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long prioCodigo = FacesUtils.checkLong(txtPrioCodigo);
            entity = businessDelegatorView.getVtPrioridad(prioCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtPrioridad(entity);
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
            selectedVtPrioridad = (VtPrioridadDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedVtPrioridad"));

            Long prioCodigo = new Long(selectedVtPrioridad.getPrioCodigo());
            entity = businessDelegatorView.getVtPrioridad(prioCodigo);
            businessDelegatorView.deleteVtPrioridad(entity);
            data.remove(selectedVtPrioridad);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, String nombre, Long prioCodigo,
        Long usuCreador, Long usuModificador) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtPrioridad(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtPrioridadView").requestRender();
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

    public InputText getTxtPrioCodigo() {
        return txtPrioCodigo;
    }

    public void setTxtPrioCodigo(InputText txtPrioCodigo) {
        this.txtPrioCodigo = txtPrioCodigo;
    }

    public List<VtPrioridadDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtPrioridad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtPrioridadDTO> vtPrioridadDTO) {
        this.data = vtPrioridadDTO;
    }

    public VtPrioridadDTO getSelectedVtPrioridad() {
        return selectedVtPrioridad;
    }

    public void setSelectedVtPrioridad(VtPrioridadDTO vtPrioridad) {
        this.selectedVtPrioridad = vtPrioridad;
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
