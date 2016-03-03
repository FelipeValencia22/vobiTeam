package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtSprintDTO;
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
public class VtSprintView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtSprintView.class);
    private InputText txtActivo;
    private InputText txtNombre;
    private InputText txtObjetivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtPilaCodigo_VtPilaProducto;
    private InputText txtSpriCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaFin;
    private Calendar txtFechaInicio;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtSprintDTO> data;
    private VtSprintDTO selectedVtSprint;
    private VtSprint entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtSprintView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtSprintDTO vtSprintDTO = (VtSprintDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtSprintDTO.getActivo());

            if (txtNombre == null) {
                txtNombre = new InputText();
            }

            txtNombre.setValue(vtSprintDTO.getNombre());

            if (txtObjetivo == null) {
                txtObjetivo = new InputText();
            }

            txtObjetivo.setValue(vtSprintDTO.getObjetivo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtSprintDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtSprintDTO.getUsuModificador());

            if (txtPilaCodigo_VtPilaProducto == null) {
                txtPilaCodigo_VtPilaProducto = new InputText();
            }

            txtPilaCodigo_VtPilaProducto.setValue(vtSprintDTO.getPilaCodigo_VtPilaProducto());

            if (txtSpriCodigo == null) {
                txtSpriCodigo = new InputText();
            }

            txtSpriCodigo.setValue(vtSprintDTO.getSpriCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtSprintDTO.getFechaCreacion());

            if (txtFechaFin == null) {
                txtFechaFin = new Calendar();
            }

            txtFechaFin.setValue(vtSprintDTO.getFechaFin());

            if (txtFechaInicio == null) {
                txtFechaInicio = new Calendar();
            }

            txtFechaInicio.setValue(vtSprintDTO.getFechaInicio());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtSprintDTO.getFechaModificacion());

            Long spriCodigo = FacesUtils.checkLong(txtSpriCodigo);
            entity = businessDelegatorView.getVtSprint(spriCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtSprint = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtSprint = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtObjetivo != null) {
            txtObjetivo.setValue(null);
            txtObjetivo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtPilaCodigo_VtPilaProducto != null) {
            txtPilaCodigo_VtPilaProducto.setValue(null);
            txtPilaCodigo_VtPilaProducto.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaFin != null) {
            txtFechaFin.setValue(null);
            txtFechaFin.setDisabled(true);
        }

        if (txtFechaInicio != null) {
            txtFechaInicio.setValue(null);
            txtFechaInicio.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtSpriCodigo != null) {
            txtSpriCodigo.setValue(null);
            txtSpriCodigo.setDisabled(false);
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

    public void listener_txtFechaFin() {
        Date inputDate = (Date) txtFechaFin.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaInicio() {
        Date inputDate = (Date) txtFechaInicio.getValue();
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
            Long spriCodigo = FacesUtils.checkLong(txtSpriCodigo);
            entity = (spriCodigo != null)
                ? businessDelegatorView.getVtSprint(spriCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtNombre.setDisabled(false);
            txtObjetivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtPilaCodigo_VtPilaProducto.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtSpriCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaFin.setValue(entity.getFechaFin());
            txtFechaFin.setDisabled(false);
            txtFechaInicio.setValue(entity.getFechaInicio());
            txtFechaInicio.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtObjetivo.setValue(entity.getObjetivo());
            txtObjetivo.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtPilaCodigo_VtPilaProducto.setValue(entity.getVtPilaProducto()
                                                        .getPilaCodigo());
            txtPilaCodigo_VtPilaProducto.setDisabled(false);
            txtSpriCodigo.setValue(entity.getSpriCodigo());
            txtSpriCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtSprint = (VtSprintDTO) (evt.getComponent().getAttributes()
                                             .get("selectedVtSprint"));
        txtActivo.setValue(selectedVtSprint.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtSprint.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaFin.setValue(selectedVtSprint.getFechaFin());
        txtFechaFin.setDisabled(false);
        txtFechaInicio.setValue(selectedVtSprint.getFechaInicio());
        txtFechaInicio.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtSprint.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtNombre.setValue(selectedVtSprint.getNombre());
        txtNombre.setDisabled(false);
        txtObjetivo.setValue(selectedVtSprint.getObjetivo());
        txtObjetivo.setDisabled(false);
        txtUsuCreador.setValue(selectedVtSprint.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtSprint.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtPilaCodigo_VtPilaProducto.setValue(selectedVtSprint.getPilaCodigo_VtPilaProducto());
        txtPilaCodigo_VtPilaProducto.setDisabled(false);
        txtSpriCodigo.setValue(selectedVtSprint.getSpriCodigo());
        txtSpriCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtSprint == null) && (entity == null)) {
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
            entity = new VtSprint();

            Long spriCodigo = FacesUtils.checkLong(txtSpriCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setObjetivo(FacesUtils.checkString(txtObjetivo));
            entity.setSpriCodigo(spriCodigo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtPilaProducto((FacesUtils.checkLong(
                    txtPilaCodigo_VtPilaProducto) != null)
                ? businessDelegatorView.getVtPilaProducto(FacesUtils.checkLong(
                        txtPilaCodigo_VtPilaProducto)) : null);
            businessDelegatorView.saveVtSprint(entity);
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
                Long spriCodigo = new Long(selectedVtSprint.getSpriCodigo());
                entity = businessDelegatorView.getVtSprint(spriCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaFin(FacesUtils.checkDate(txtFechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(txtFechaInicio));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setObjetivo(FacesUtils.checkString(txtObjetivo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtPilaProducto((FacesUtils.checkLong(
                    txtPilaCodigo_VtPilaProducto) != null)
                ? businessDelegatorView.getVtPilaProducto(FacesUtils.checkLong(
                        txtPilaCodigo_VtPilaProducto)) : null);
            businessDelegatorView.updateVtSprint(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtSprint = (VtSprintDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedVtSprint"));

            Long spriCodigo = new Long(selectedVtSprint.getSpriCodigo());
            entity = businessDelegatorView.getVtSprint(spriCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long spriCodigo = FacesUtils.checkLong(txtSpriCodigo);
            entity = businessDelegatorView.getVtSprint(spriCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtSprint(entity);
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
            selectedVtSprint = (VtSprintDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedVtSprint"));

            Long spriCodigo = new Long(selectedVtSprint.getSpriCodigo());
            entity = businessDelegatorView.getVtSprint(spriCodigo);
            businessDelegatorView.deleteVtSprint(entity);
            data.remove(selectedVtSprint);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaFin, Date fechaInicio, Date fechaModificacion, String nombre,
        String objetivo, Long spriCodigo, Long usuCreador, Long usuModificador,
        Long pilaCodigo_VtPilaProducto) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaFin(FacesUtils.checkDate(fechaFin));
            entity.setFechaInicio(FacesUtils.checkDate(fechaInicio));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setNombre(FacesUtils.checkString(nombre));
            entity.setObjetivo(FacesUtils.checkString(objetivo));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtSprint(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtSprintView").requestRender();
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

    public InputText getTxtObjetivo() {
        return txtObjetivo;
    }

    public void setTxtObjetivo(InputText txtObjetivo) {
        this.txtObjetivo = txtObjetivo;
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

    public InputText getTxtPilaCodigo_VtPilaProducto() {
        return txtPilaCodigo_VtPilaProducto;
    }

    public void setTxtPilaCodigo_VtPilaProducto(
        InputText txtPilaCodigo_VtPilaProducto) {
        this.txtPilaCodigo_VtPilaProducto = txtPilaCodigo_VtPilaProducto;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaFin() {
        return txtFechaFin;
    }

    public void setTxtFechaFin(Calendar txtFechaFin) {
        this.txtFechaFin = txtFechaFin;
    }

    public Calendar getTxtFechaInicio() {
        return txtFechaInicio;
    }

    public void setTxtFechaInicio(Calendar txtFechaInicio) {
        this.txtFechaInicio = txtFechaInicio;
    }

    public Calendar getTxtFechaModificacion() {
        return txtFechaModificacion;
    }

    public void setTxtFechaModificacion(Calendar txtFechaModificacion) {
        this.txtFechaModificacion = txtFechaModificacion;
    }

    public InputText getTxtSpriCodigo() {
        return txtSpriCodigo;
    }

    public void setTxtSpriCodigo(InputText txtSpriCodigo) {
        this.txtSpriCodigo = txtSpriCodigo;
    }

    public List<VtSprintDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtSprint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtSprintDTO> vtSprintDTO) {
        this.data = vtSprintDTO;
    }

    public VtSprintDTO getSelectedVtSprint() {
        return selectedVtSprint;
    }

    public void setSelectedVtSprint(VtSprintDTO vtSprint) {
        this.selectedVtSprint = vtSprint;
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
