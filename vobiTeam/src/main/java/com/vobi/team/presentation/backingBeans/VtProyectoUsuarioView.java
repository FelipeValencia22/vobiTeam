package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtProyectoUsuarioDTO;
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
public class VtProyectoUsuarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtProyectoUsuarioView.class);
    private InputText txtActivo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtProyCodigo_VtProyecto;
    private InputText txtUsuaCodigo_VtUsuario;
    private InputText txtPrusCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtProyectoUsuarioDTO> data;
    private VtProyectoUsuarioDTO selectedVtProyectoUsuario;
    private VtProyectoUsuario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtProyectoUsuarioView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtProyectoUsuarioDTO vtProyectoUsuarioDTO = (VtProyectoUsuarioDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtProyectoUsuarioDTO.getActivo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtProyectoUsuarioDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtProyectoUsuarioDTO.getUsuModificador());

            if (txtProyCodigo_VtProyecto == null) {
                txtProyCodigo_VtProyecto = new InputText();
            }

            txtProyCodigo_VtProyecto.setValue(vtProyectoUsuarioDTO.getProyCodigo_VtProyecto());

            if (txtUsuaCodigo_VtUsuario == null) {
                txtUsuaCodigo_VtUsuario = new InputText();
            }

            txtUsuaCodigo_VtUsuario.setValue(vtProyectoUsuarioDTO.getUsuaCodigo_VtUsuario());

            if (txtPrusCodigo == null) {
                txtPrusCodigo = new InputText();
            }

            txtPrusCodigo.setValue(vtProyectoUsuarioDTO.getPrusCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtProyectoUsuarioDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtProyectoUsuarioDTO.getFechaModificacion());

            Long prusCodigo = FacesUtils.checkLong(txtPrusCodigo);
            entity = businessDelegatorView.getVtProyectoUsuario(prusCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtProyectoUsuario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtProyectoUsuario = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtProyCodigo_VtProyecto != null) {
            txtProyCodigo_VtProyecto.setValue(null);
            txtProyCodigo_VtProyecto.setDisabled(true);
        }

        if (txtUsuaCodigo_VtUsuario != null) {
            txtUsuaCodigo_VtUsuario.setValue(null);
            txtUsuaCodigo_VtUsuario.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtPrusCodigo != null) {
            txtPrusCodigo.setValue(null);
            txtPrusCodigo.setDisabled(false);
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
            Long prusCodigo = FacesUtils.checkLong(txtPrusCodigo);
            entity = (prusCodigo != null)
                ? businessDelegatorView.getVtProyectoUsuario(prusCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtProyCodigo_VtProyecto.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtPrusCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtProyCodigo_VtProyecto.setValue(entity.getVtProyecto()
                                                    .getProyCodigo());
            txtProyCodigo_VtProyecto.setDisabled(false);
            txtUsuaCodigo_VtUsuario.setValue(entity.getVtUsuario()
                                                   .getUsuaCodigo());
            txtUsuaCodigo_VtUsuario.setDisabled(false);
            txtPrusCodigo.setValue(entity.getPrusCodigo());
            txtPrusCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtProyectoUsuario = (VtProyectoUsuarioDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedVtProyectoUsuario"));
        txtActivo.setValue(selectedVtProyectoUsuario.getActivo());
        txtActivo.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtProyectoUsuario.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtProyectoUsuario.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtUsuCreador.setValue(selectedVtProyectoUsuario.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtProyectoUsuario.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtProyCodigo_VtProyecto.setValue(selectedVtProyectoUsuario.getProyCodigo_VtProyecto());
        txtProyCodigo_VtProyecto.setDisabled(false);
        txtUsuaCodigo_VtUsuario.setValue(selectedVtProyectoUsuario.getUsuaCodigo_VtUsuario());
        txtUsuaCodigo_VtUsuario.setDisabled(false);
        txtPrusCodigo.setValue(selectedVtProyectoUsuario.getPrusCodigo());
        txtPrusCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtProyectoUsuario == null) && (entity == null)) {
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
            entity = new VtProyectoUsuario();

            Long prusCodigo = FacesUtils.checkLong(txtPrusCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setPrusCodigo(prusCodigo);
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtProyecto((FacesUtils.checkLong(txtProyCodigo_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyCodigo_VtProyecto)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.saveVtProyectoUsuario(entity);
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
                Long prusCodigo = new Long(selectedVtProyectoUsuario.getPrusCodigo());
                entity = businessDelegatorView.getVtProyectoUsuario(prusCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtProyecto((FacesUtils.checkLong(txtProyCodigo_VtProyecto) != null)
                ? businessDelegatorView.getVtProyecto(FacesUtils.checkLong(
                        txtProyCodigo_VtProyecto)) : null);
            entity.setVtUsuario((FacesUtils.checkLong(txtUsuaCodigo_VtUsuario) != null)
                ? businessDelegatorView.getVtUsuario(FacesUtils.checkLong(
                        txtUsuaCodigo_VtUsuario)) : null);
            businessDelegatorView.updateVtProyectoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtProyectoUsuario = (VtProyectoUsuarioDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedVtProyectoUsuario"));

            Long prusCodigo = new Long(selectedVtProyectoUsuario.getPrusCodigo());
            entity = businessDelegatorView.getVtProyectoUsuario(prusCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long prusCodigo = FacesUtils.checkLong(txtPrusCodigo);
            entity = businessDelegatorView.getVtProyectoUsuario(prusCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtProyectoUsuario(entity);
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
            selectedVtProyectoUsuario = (VtProyectoUsuarioDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedVtProyectoUsuario"));

            Long prusCodigo = new Long(selectedVtProyectoUsuario.getPrusCodigo());
            entity = businessDelegatorView.getVtProyectoUsuario(prusCodigo);
            businessDelegatorView.deleteVtProyectoUsuario(entity);
            data.remove(selectedVtProyectoUsuario);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Date fechaCreacion,
        Date fechaModificacion, Long prusCodigo, Long usuCreador,
        Long usuModificador, Long proyCodigo_VtProyecto,
        Long usuaCodigo_VtUsuario) throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtProyectoUsuario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtProyectoUsuarioView").requestRender();
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

    public InputText getTxtProyCodigo_VtProyecto() {
        return txtProyCodigo_VtProyecto;
    }

    public void setTxtProyCodigo_VtProyecto(InputText txtProyCodigo_VtProyecto) {
        this.txtProyCodigo_VtProyecto = txtProyCodigo_VtProyecto;
    }

    public InputText getTxtUsuaCodigo_VtUsuario() {
        return txtUsuaCodigo_VtUsuario;
    }

    public void setTxtUsuaCodigo_VtUsuario(InputText txtUsuaCodigo_VtUsuario) {
        this.txtUsuaCodigo_VtUsuario = txtUsuaCodigo_VtUsuario;
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

    public InputText getTxtPrusCodigo() {
        return txtPrusCodigo;
    }

    public void setTxtPrusCodigo(InputText txtPrusCodigo) {
        this.txtPrusCodigo = txtPrusCodigo;
    }

    public List<VtProyectoUsuarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtProyectoUsuario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtProyectoUsuarioDTO> vtProyectoUsuarioDTO) {
        this.data = vtProyectoUsuarioDTO;
    }

    public VtProyectoUsuarioDTO getSelectedVtProyectoUsuario() {
        return selectedVtProyectoUsuario;
    }

    public void setSelectedVtProyectoUsuario(
        VtProyectoUsuarioDTO vtProyectoUsuario) {
        this.selectedVtProyectoUsuario = vtProyectoUsuario;
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
