package com.vobi.team.presentation.backingBeans;

import com.vobi.team.exceptions.*;
import com.vobi.team.modelo.*;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
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
public class VtArtefactoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VtArtefactoView.class);
    private InputText txtActivo;
    private InputText txtDescripcion;
    private InputText txtEsfuerzoEstimado;
    private InputText txtEsfuerzoReal;
    private InputText txtEsfuerzoRestante;
    private InputText txtOrigen;
    private InputText txtPuntos;
    private InputText txtTitulo;
    private InputText txtUsuCreador;
    private InputText txtUsuModificador;
    private InputText txtEstaCodigo_VtEstado;
    private InputText txtPilaCodigo_VtPilaProducto;
    private InputText txtPrioCodigo_VtPrioridad;
    private InputText txtSpriCodigo_VtSprint;
    private InputText txtTparCodigo_VtTipoArtefacto;
    private InputText txtArteCodigo;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<VtArtefactoDTO> data;
    private VtArtefactoDTO selectedVtArtefacto;
    private VtArtefacto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public VtArtefactoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            VtArtefactoDTO vtArtefactoDTO = (VtArtefactoDTO) e.getObject();

            if (txtActivo == null) {
                txtActivo = new InputText();
            }

            txtActivo.setValue(vtArtefactoDTO.getActivo());

            if (txtDescripcion == null) {
                txtDescripcion = new InputText();
            }

            txtDescripcion.setValue(vtArtefactoDTO.getDescripcion());

            if (txtEsfuerzoEstimado == null) {
                txtEsfuerzoEstimado = new InputText();
            }

            txtEsfuerzoEstimado.setValue(vtArtefactoDTO.getEsfuerzoEstimado());

            if (txtEsfuerzoReal == null) {
                txtEsfuerzoReal = new InputText();
            }

            txtEsfuerzoReal.setValue(vtArtefactoDTO.getEsfuerzoReal());

            if (txtEsfuerzoRestante == null) {
                txtEsfuerzoRestante = new InputText();
            }

            txtEsfuerzoRestante.setValue(vtArtefactoDTO.getEsfuerzoRestante());

            if (txtOrigen == null) {
                txtOrigen = new InputText();
            }

            txtOrigen.setValue(vtArtefactoDTO.getOrigen());

            if (txtPuntos == null) {
                txtPuntos = new InputText();
            }

            txtPuntos.setValue(vtArtefactoDTO.getPuntos());

            if (txtTitulo == null) {
                txtTitulo = new InputText();
            }

            txtTitulo.setValue(vtArtefactoDTO.getTitulo());

            if (txtUsuCreador == null) {
                txtUsuCreador = new InputText();
            }

            txtUsuCreador.setValue(vtArtefactoDTO.getUsuCreador());

            if (txtUsuModificador == null) {
                txtUsuModificador = new InputText();
            }

            txtUsuModificador.setValue(vtArtefactoDTO.getUsuModificador());

            if (txtEstaCodigo_VtEstado == null) {
                txtEstaCodigo_VtEstado = new InputText();
            }

            txtEstaCodigo_VtEstado.setValue(vtArtefactoDTO.getEstaCodigo_VtEstado());

            if (txtPilaCodigo_VtPilaProducto == null) {
                txtPilaCodigo_VtPilaProducto = new InputText();
            }

            txtPilaCodigo_VtPilaProducto.setValue(vtArtefactoDTO.getPilaCodigo_VtPilaProducto());

            if (txtPrioCodigo_VtPrioridad == null) {
                txtPrioCodigo_VtPrioridad = new InputText();
            }

            txtPrioCodigo_VtPrioridad.setValue(vtArtefactoDTO.getPrioCodigo_VtPrioridad());

            if (txtSpriCodigo_VtSprint == null) {
                txtSpriCodigo_VtSprint = new InputText();
            }

            txtSpriCodigo_VtSprint.setValue(vtArtefactoDTO.getSpriCodigo_VtSprint());

            if (txtTparCodigo_VtTipoArtefacto == null) {
                txtTparCodigo_VtTipoArtefacto = new InputText();
            }

            txtTparCodigo_VtTipoArtefacto.setValue(vtArtefactoDTO.getTparCodigo_VtTipoArtefacto());

            if (txtArteCodigo == null) {
                txtArteCodigo = new InputText();
            }

            txtArteCodigo.setValue(vtArtefactoDTO.getArteCodigo());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(vtArtefactoDTO.getFechaCreacion());

            if (txtFechaModificacion == null) {
                txtFechaModificacion = new Calendar();
            }

            txtFechaModificacion.setValue(vtArtefactoDTO.getFechaModificacion());

            Long arteCodigo = FacesUtils.checkLong(txtArteCodigo);
            entity = businessDelegatorView.getVtArtefacto(arteCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedVtArtefacto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedVtArtefacto = null;

        if (txtActivo != null) {
            txtActivo.setValue(null);
            txtActivo.setDisabled(true);
        }

        if (txtDescripcion != null) {
            txtDescripcion.setValue(null);
            txtDescripcion.setDisabled(true);
        }

        if (txtEsfuerzoEstimado != null) {
            txtEsfuerzoEstimado.setValue(null);
            txtEsfuerzoEstimado.setDisabled(true);
        }

        if (txtEsfuerzoReal != null) {
            txtEsfuerzoReal.setValue(null);
            txtEsfuerzoReal.setDisabled(true);
        }

        if (txtEsfuerzoRestante != null) {
            txtEsfuerzoRestante.setValue(null);
            txtEsfuerzoRestante.setDisabled(true);
        }

        if (txtOrigen != null) {
            txtOrigen.setValue(null);
            txtOrigen.setDisabled(true);
        }

        if (txtPuntos != null) {
            txtPuntos.setValue(null);
            txtPuntos.setDisabled(true);
        }

        if (txtTitulo != null) {
            txtTitulo.setValue(null);
            txtTitulo.setDisabled(true);
        }

        if (txtUsuCreador != null) {
            txtUsuCreador.setValue(null);
            txtUsuCreador.setDisabled(true);
        }

        if (txtUsuModificador != null) {
            txtUsuModificador.setValue(null);
            txtUsuModificador.setDisabled(true);
        }

        if (txtEstaCodigo_VtEstado != null) {
            txtEstaCodigo_VtEstado.setValue(null);
            txtEstaCodigo_VtEstado.setDisabled(true);
        }

        if (txtPilaCodigo_VtPilaProducto != null) {
            txtPilaCodigo_VtPilaProducto.setValue(null);
            txtPilaCodigo_VtPilaProducto.setDisabled(true);
        }

        if (txtPrioCodigo_VtPrioridad != null) {
            txtPrioCodigo_VtPrioridad.setValue(null);
            txtPrioCodigo_VtPrioridad.setDisabled(true);
        }

        if (txtSpriCodigo_VtSprint != null) {
            txtSpriCodigo_VtSprint.setValue(null);
            txtSpriCodigo_VtSprint.setDisabled(true);
        }

        if (txtTparCodigo_VtTipoArtefacto != null) {
            txtTparCodigo_VtTipoArtefacto.setValue(null);
            txtTparCodigo_VtTipoArtefacto.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaModificacion != null) {
            txtFechaModificacion.setValue(null);
            txtFechaModificacion.setDisabled(true);
        }

        if (txtArteCodigo != null) {
            txtArteCodigo.setValue(null);
            txtArteCodigo.setDisabled(false);
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
            Long arteCodigo = FacesUtils.checkLong(txtArteCodigo);
            entity = (arteCodigo != null)
                ? businessDelegatorView.getVtArtefacto(arteCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtActivo.setDisabled(false);
            txtDescripcion.setDisabled(false);
            txtEsfuerzoEstimado.setDisabled(false);
            txtEsfuerzoReal.setDisabled(false);
            txtEsfuerzoRestante.setDisabled(false);
            txtOrigen.setDisabled(false);
            txtPuntos.setDisabled(false);
            txtTitulo.setDisabled(false);
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setDisabled(false);
            txtEstaCodigo_VtEstado.setDisabled(false);
            txtPilaCodigo_VtPilaProducto.setDisabled(false);
            txtPrioCodigo_VtPrioridad.setDisabled(false);
            txtSpriCodigo_VtSprint.setDisabled(false);
            txtTparCodigo_VtTipoArtefacto.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setDisabled(false);
            txtArteCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtActivo.setValue(entity.getActivo());
            txtActivo.setDisabled(false);
            txtDescripcion.setValue(entity.getDescripcion());
            txtDescripcion.setDisabled(false);
            txtEsfuerzoEstimado.setValue(entity.getEsfuerzoEstimado());
            txtEsfuerzoEstimado.setDisabled(false);
            txtEsfuerzoReal.setValue(entity.getEsfuerzoReal());
            txtEsfuerzoReal.setDisabled(false);
            txtEsfuerzoRestante.setValue(entity.getEsfuerzoRestante());
            txtEsfuerzoRestante.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaModificacion.setValue(entity.getFechaModificacion());
            txtFechaModificacion.setDisabled(false);
            txtOrigen.setValue(entity.getOrigen());
            txtOrigen.setDisabled(false);
            txtPuntos.setValue(entity.getPuntos());
            txtPuntos.setDisabled(false);
            txtTitulo.setValue(entity.getTitulo());
            txtTitulo.setDisabled(false);
            txtUsuCreador.setValue(entity.getUsuCreador());
            txtUsuCreador.setDisabled(false);
            txtUsuModificador.setValue(entity.getUsuModificador());
            txtUsuModificador.setDisabled(false);
            txtEstaCodigo_VtEstado.setValue(entity.getVtEstado().getEstaCodigo());
            txtEstaCodigo_VtEstado.setDisabled(false);
            txtPilaCodigo_VtPilaProducto.setValue(entity.getVtPilaProducto()
                                                        .getPilaCodigo());
            txtPilaCodigo_VtPilaProducto.setDisabled(false);
            txtPrioCodigo_VtPrioridad.setValue(entity.getVtPrioridad()
                                                     .getPrioCodigo());
            txtPrioCodigo_VtPrioridad.setDisabled(false);
            txtSpriCodigo_VtSprint.setValue(entity.getVtSprint().getSpriCodigo());
            txtSpriCodigo_VtSprint.setDisabled(false);
            txtTparCodigo_VtTipoArtefacto.setValue(entity.getVtTipoArtefacto()
                                                         .getTparCodigo());
            txtTparCodigo_VtTipoArtefacto.setDisabled(false);
            txtArteCodigo.setValue(entity.getArteCodigo());
            txtArteCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedVtArtefacto"));
        txtActivo.setValue(selectedVtArtefacto.getActivo());
        txtActivo.setDisabled(false);
        txtDescripcion.setValue(selectedVtArtefacto.getDescripcion());
        txtDescripcion.setDisabled(false);
        txtEsfuerzoEstimado.setValue(selectedVtArtefacto.getEsfuerzoEstimado());
        txtEsfuerzoEstimado.setDisabled(false);
        txtEsfuerzoReal.setValue(selectedVtArtefacto.getEsfuerzoReal());
        txtEsfuerzoReal.setDisabled(false);
        txtEsfuerzoRestante.setValue(selectedVtArtefacto.getEsfuerzoRestante());
        txtEsfuerzoRestante.setDisabled(false);
        txtFechaCreacion.setValue(selectedVtArtefacto.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaModificacion.setValue(selectedVtArtefacto.getFechaModificacion());
        txtFechaModificacion.setDisabled(false);
        txtOrigen.setValue(selectedVtArtefacto.getOrigen());
        txtOrigen.setDisabled(false);
        txtPuntos.setValue(selectedVtArtefacto.getPuntos());
        txtPuntos.setDisabled(false);
        txtTitulo.setValue(selectedVtArtefacto.getTitulo());
        txtTitulo.setDisabled(false);
        txtUsuCreador.setValue(selectedVtArtefacto.getUsuCreador());
        txtUsuCreador.setDisabled(false);
        txtUsuModificador.setValue(selectedVtArtefacto.getUsuModificador());
        txtUsuModificador.setDisabled(false);
        txtEstaCodigo_VtEstado.setValue(selectedVtArtefacto.getEstaCodigo_VtEstado());
        txtEstaCodigo_VtEstado.setDisabled(false);
        txtPilaCodigo_VtPilaProducto.setValue(selectedVtArtefacto.getPilaCodigo_VtPilaProducto());
        txtPilaCodigo_VtPilaProducto.setDisabled(false);
        txtPrioCodigo_VtPrioridad.setValue(selectedVtArtefacto.getPrioCodigo_VtPrioridad());
        txtPrioCodigo_VtPrioridad.setDisabled(false);
        txtSpriCodigo_VtSprint.setValue(selectedVtArtefacto.getSpriCodigo_VtSprint());
        txtSpriCodigo_VtSprint.setDisabled(false);
        txtTparCodigo_VtTipoArtefacto.setValue(selectedVtArtefacto.getTparCodigo_VtTipoArtefacto());
        txtTparCodigo_VtTipoArtefacto.setDisabled(false);
        txtArteCodigo.setValue(selectedVtArtefacto.getArteCodigo());
        txtArteCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedVtArtefacto == null) && (entity == null)) {
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
            entity = new VtArtefacto();

            Long arteCodigo = FacesUtils.checkLong(txtArteCodigo);

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setArteCodigo(arteCodigo);
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkInteger(
                    txtEsfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(txtEsfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(
                    txtEsfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setOrigen(FacesUtils.checkString(txtOrigen));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTitulo(FacesUtils.checkString(txtTitulo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtEstado((FacesUtils.checkLong(txtEstaCodigo_VtEstado) != null)
                ? businessDelegatorView.getVtEstado(FacesUtils.checkLong(
                        txtEstaCodigo_VtEstado)) : null);
            entity.setVtPilaProducto((FacesUtils.checkLong(
                    txtPilaCodigo_VtPilaProducto) != null)
                ? businessDelegatorView.getVtPilaProducto(FacesUtils.checkLong(
                        txtPilaCodigo_VtPilaProducto)) : null);
            entity.setVtPrioridad((FacesUtils.checkLong(
                    txtPrioCodigo_VtPrioridad) != null)
                ? businessDelegatorView.getVtPrioridad(FacesUtils.checkLong(
                        txtPrioCodigo_VtPrioridad)) : null);
            entity.setVtSprint((FacesUtils.checkLong(txtSpriCodigo_VtSprint) != null)
                ? businessDelegatorView.getVtSprint(FacesUtils.checkLong(
                        txtSpriCodigo_VtSprint)) : null);
            entity.setVtTipoArtefacto((FacesUtils.checkLong(
                    txtTparCodigo_VtTipoArtefacto) != null)
                ? businessDelegatorView.getVtTipoArtefacto(FacesUtils.checkLong(
                        txtTparCodigo_VtTipoArtefacto)) : null);
            businessDelegatorView.saveVtArtefacto(entity);
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
                Long arteCodigo = new Long(selectedVtArtefacto.getArteCodigo());
                entity = businessDelegatorView.getVtArtefacto(arteCodigo);
            }

            entity.setActivo(FacesUtils.checkString(txtActivo));
            entity.setDescripcion(FacesUtils.checkString(txtDescripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkInteger(
                    txtEsfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(txtEsfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(
                    txtEsfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(
                    txtFechaModificacion));
            entity.setOrigen(FacesUtils.checkString(txtOrigen));
            entity.setPuntos(FacesUtils.checkInteger(txtPuntos));
            entity.setTitulo(FacesUtils.checkString(txtTitulo));
            entity.setUsuCreador(FacesUtils.checkLong(txtUsuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(txtUsuModificador));
            entity.setVtEstado((FacesUtils.checkLong(txtEstaCodigo_VtEstado) != null)
                ? businessDelegatorView.getVtEstado(FacesUtils.checkLong(
                        txtEstaCodigo_VtEstado)) : null);
            entity.setVtPilaProducto((FacesUtils.checkLong(
                    txtPilaCodigo_VtPilaProducto) != null)
                ? businessDelegatorView.getVtPilaProducto(FacesUtils.checkLong(
                        txtPilaCodigo_VtPilaProducto)) : null);
            entity.setVtPrioridad((FacesUtils.checkLong(
                    txtPrioCodigo_VtPrioridad) != null)
                ? businessDelegatorView.getVtPrioridad(FacesUtils.checkLong(
                        txtPrioCodigo_VtPrioridad)) : null);
            entity.setVtSprint((FacesUtils.checkLong(txtSpriCodigo_VtSprint) != null)
                ? businessDelegatorView.getVtSprint(FacesUtils.checkLong(
                        txtSpriCodigo_VtSprint)) : null);
            entity.setVtTipoArtefacto((FacesUtils.checkLong(
                    txtTparCodigo_VtTipoArtefacto) != null)
                ? businessDelegatorView.getVtTipoArtefacto(FacesUtils.checkLong(
                        txtTparCodigo_VtTipoArtefacto)) : null);
            businessDelegatorView.updateVtArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedVtArtefacto"));

            Long arteCodigo = new Long(selectedVtArtefacto.getArteCodigo());
            entity = businessDelegatorView.getVtArtefacto(arteCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long arteCodigo = FacesUtils.checkLong(txtArteCodigo);
            entity = businessDelegatorView.getVtArtefacto(arteCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteVtArtefacto(entity);
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
            selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedVtArtefacto"));

            Long arteCodigo = new Long(selectedVtArtefacto.getArteCodigo());
            entity = businessDelegatorView.getVtArtefacto(arteCodigo);
            businessDelegatorView.deleteVtArtefacto(entity);
            data.remove(selectedVtArtefacto);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String activo, Long arteCodigo,
        String descripcion, Integer esfuerzoEstimado, Integer esfuerzoReal,
        Integer esfuerzoRestante, Date fechaCreacion, Date fechaModificacion,
        String origen, Integer puntos, String titulo, Long usuCreador,
        Long usuModificador, Long estaCodigo_VtEstado,
        Long pilaCodigo_VtPilaProducto, Long prioCodigo_VtPrioridad,
        Long spriCodigo_VtSprint, Long tparCodigo_VtTipoArtefacto)
        throws Exception {
        try {
            entity.setActivo(FacesUtils.checkString(activo));
            entity.setDescripcion(FacesUtils.checkString(descripcion));
            entity.setEsfuerzoEstimado(FacesUtils.checkInteger(esfuerzoEstimado));
            entity.setEsfuerzoReal(FacesUtils.checkInteger(esfuerzoReal));
            entity.setEsfuerzoRestante(FacesUtils.checkInteger(esfuerzoRestante));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaModificacion(FacesUtils.checkDate(fechaModificacion));
            entity.setOrigen(FacesUtils.checkString(origen));
            entity.setPuntos(FacesUtils.checkInteger(puntos));
            entity.setTitulo(FacesUtils.checkString(titulo));
            entity.setUsuCreador(FacesUtils.checkLong(usuCreador));
            entity.setUsuModificador(FacesUtils.checkLong(usuModificador));
            businessDelegatorView.updateVtArtefacto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("VtArtefactoView").requestRender();
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

    public InputText getTxtDescripcion() {
        return txtDescripcion;
    }

    public void setTxtDescripcion(InputText txtDescripcion) {
        this.txtDescripcion = txtDescripcion;
    }

    public InputText getTxtEsfuerzoEstimado() {
        return txtEsfuerzoEstimado;
    }

    public void setTxtEsfuerzoEstimado(InputText txtEsfuerzoEstimado) {
        this.txtEsfuerzoEstimado = txtEsfuerzoEstimado;
    }

    public InputText getTxtEsfuerzoReal() {
        return txtEsfuerzoReal;
    }

    public void setTxtEsfuerzoReal(InputText txtEsfuerzoReal) {
        this.txtEsfuerzoReal = txtEsfuerzoReal;
    }

    public InputText getTxtEsfuerzoRestante() {
        return txtEsfuerzoRestante;
    }

    public void setTxtEsfuerzoRestante(InputText txtEsfuerzoRestante) {
        this.txtEsfuerzoRestante = txtEsfuerzoRestante;
    }

    public InputText getTxtOrigen() {
        return txtOrigen;
    }

    public void setTxtOrigen(InputText txtOrigen) {
        this.txtOrigen = txtOrigen;
    }

    public InputText getTxtPuntos() {
        return txtPuntos;
    }

    public void setTxtPuntos(InputText txtPuntos) {
        this.txtPuntos = txtPuntos;
    }

    public InputText getTxtTitulo() {
        return txtTitulo;
    }

    public void setTxtTitulo(InputText txtTitulo) {
        this.txtTitulo = txtTitulo;
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

    public InputText getTxtEstaCodigo_VtEstado() {
        return txtEstaCodigo_VtEstado;
    }

    public void setTxtEstaCodigo_VtEstado(InputText txtEstaCodigo_VtEstado) {
        this.txtEstaCodigo_VtEstado = txtEstaCodigo_VtEstado;
    }

    public InputText getTxtPilaCodigo_VtPilaProducto() {
        return txtPilaCodigo_VtPilaProducto;
    }

    public void setTxtPilaCodigo_VtPilaProducto(
        InputText txtPilaCodigo_VtPilaProducto) {
        this.txtPilaCodigo_VtPilaProducto = txtPilaCodigo_VtPilaProducto;
    }

    public InputText getTxtPrioCodigo_VtPrioridad() {
        return txtPrioCodigo_VtPrioridad;
    }

    public void setTxtPrioCodigo_VtPrioridad(
        InputText txtPrioCodigo_VtPrioridad) {
        this.txtPrioCodigo_VtPrioridad = txtPrioCodigo_VtPrioridad;
    }

    public InputText getTxtSpriCodigo_VtSprint() {
        return txtSpriCodigo_VtSprint;
    }

    public void setTxtSpriCodigo_VtSprint(InputText txtSpriCodigo_VtSprint) {
        this.txtSpriCodigo_VtSprint = txtSpriCodigo_VtSprint;
    }

    public InputText getTxtTparCodigo_VtTipoArtefacto() {
        return txtTparCodigo_VtTipoArtefacto;
    }

    public void setTxtTparCodigo_VtTipoArtefacto(
        InputText txtTparCodigo_VtTipoArtefacto) {
        this.txtTparCodigo_VtTipoArtefacto = txtTparCodigo_VtTipoArtefacto;
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

    public InputText getTxtArteCodigo() {
        return txtArteCodigo;
    }

    public void setTxtArteCodigo(InputText txtArteCodigo) {
        this.txtArteCodigo = txtArteCodigo;
    }

    public List<VtArtefactoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataVtArtefacto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<VtArtefactoDTO> vtArtefactoDTO) {
        this.data = vtArtefactoDTO;
    }

    public VtArtefactoDTO getSelectedVtArtefacto() {
        return selectedVtArtefacto;
    }

    public void setSelectedVtArtefacto(VtArtefactoDTO vtArtefacto) {
        this.selectedVtArtefacto = vtArtefacto;
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
