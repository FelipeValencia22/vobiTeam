package com.vobi.team.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.presentation.businessDelegate.IBusinessDelegatorView;
import com.vobi.team.utilities.FacesUtils;

@ManagedBean
@ViewScoped
public class VtArtefactoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(VtArtefactoView.class);

	private SelectOneMenu somActivo;
	private SelectOneMenu somEstados;
	private SelectOneMenu somPrioridades;
	private SelectOneMenu somTiposDeArtefactos;
	private SelectOneMenu somSprints;
	private SelectOneMenu somPilaProducto;
	private SelectOneMenu somUsuariosArtefactos;
	private List<SelectItem> esUsuarioArtefactoItems;
	private List<SelectItem> esPilaProductoItems;

	private List<SelectItem> esActivoItems;
	private List<SelectItem> esPrioridadItems;
	private List<SelectItem> esTipoArtefactoItems;
	private List<SelectItem> esEstadoItems;
	private List<SelectItem> esSprintItems;
	private InputTextarea txtdescripcion;
	private InputText txtnombre;
	private InputText txtEsfuerzoEstimado;
	private Calendar txtFechaFin;
	private Calendar txtFechaInicio;
	private CommandButton btnCrearS;
	private CommandButton btnCrear;
	private CommandButton btnLimpiarS;
	private CommandButton btnGuardar;
	private CommandButton btnLimpiar;

	private VtArtefacto entity;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public InputTextarea getTxtdescripcion() {
		return txtdescripcion;
	}

	public SelectOneMenu getSomUsuariosArtefactos() {
		return somUsuariosArtefactos;
	}

	public void setSomUsuariosArtefactos(SelectOneMenu somUsuariosArtefactos) {
		this.somUsuariosArtefactos = somUsuariosArtefactos;
	}

	public List<SelectItem> getEsUsuarioArtefactoItems() {
		return esUsuarioArtefactoItems;
	}

	public void setEsUsuarioArtefactoItems(List<SelectItem> esUsuarioArtefactoItems) {
		this.esUsuarioArtefactoItems = esUsuarioArtefactoItems;
	}

	public InputText getTxtEsfuerzoEstimado() {
		return txtEsfuerzoEstimado;
	}

	public void setTxtEsfuerzoEstimado(InputText txtEsfuerzoEstimado) {
		this.txtEsfuerzoEstimado = txtEsfuerzoEstimado;
	}

	public void setTxtdescripcion(InputTextarea txtdescripcion) {
		this.txtdescripcion = txtdescripcion;
	}

	public InputText getTxtnombre() {
		return txtnombre;
	}

	public void setTxtnombre(InputText txtnombre) {
		this.txtnombre = txtnombre;
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public SelectOneMenu getSomEstados() {
		return somEstados;
	}

	public void setSomEstados(SelectOneMenu somEstados) {
		this.somEstados = somEstados;
	}

	public SelectOneMenu getSomPrioridades() {
		return somPrioridades;
	}

	public void setSomPrioridades(SelectOneMenu somPrioridades) {
		this.somPrioridades = somPrioridades;
	}

	public SelectOneMenu getSomTiposDeArtefactos() {
		return somTiposDeArtefactos;
	}

	public void setSomTiposDeArtefactos(SelectOneMenu somTiposDeArtefactos) {
		this.somTiposDeArtefactos = somTiposDeArtefactos;
	}

	public SelectOneMenu getSomSprints() {
		return somSprints;
	}

	public void setSomSprints(SelectOneMenu somSprints) {
		this.somSprints = somSprints;
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

	public List<SelectItem> getEsPrioridadItems() {

		try {
			if (esPrioridadItems == null) {
				List<VtPrioridad> listaPrioridad = businessDelegatorView.getVtPrioridad();
				esPrioridadItems = new ArrayList<SelectItem>();

				for (VtPrioridad vtPrioridad : listaPrioridad) {
					esPrioridadItems.add(new SelectItem(vtPrioridad.getPrioCodigo(), vtPrioridad.getNombre()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esPrioridadItems;
	}

	public void setEsPrioridadItems(List<SelectItem> esPrioridadItems) {
		this.esPrioridadItems = esPrioridadItems;
	}

	public List<SelectItem> getEsTipoArtefactoItems() {
		try {
			if (esTipoArtefactoItems == null) {
				List<VtTipoArtefacto> listaTiposArtefacto = businessDelegatorView.getVtTipoArtefacto();
				esTipoArtefactoItems = new ArrayList<SelectItem>();
				for (VtTipoArtefacto vtTipoArtefacto : listaTiposArtefacto) {
					esTipoArtefactoItems
							.add(new SelectItem(vtTipoArtefacto.getTparCodigo(), vtTipoArtefacto.getNombre()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return esTipoArtefactoItems;
	}

	public void setEsTipoArtefactoItems(List<SelectItem> esTipoArtefactoItems) {
		this.esTipoArtefactoItems = esTipoArtefactoItems;
	}

	public List<SelectItem> getEsEstadoItems() {
		try {
			if (esEstadoItems == null) {
				List<VtEstado> listaTiposEstado = businessDelegatorView.getVtEstado();
				esEstadoItems = new ArrayList<SelectItem>();
				for (VtEstado vtEstado : listaTiposEstado) {
					esEstadoItems.add(new SelectItem(vtEstado.getEstaCodigo(), vtEstado.getNombre()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return esEstadoItems;
	}

	public void setEsEstadoItems(List<SelectItem> esEstadoItems) {
		this.esEstadoItems = esEstadoItems;
	}

	public List<SelectItem> getEsSprintItems() {
		try {
			if (esSprintItems == null) {
				List<VtSprint> listaSprints = businessDelegatorView.getVtSprint();
				esSprintItems = new ArrayList<SelectItem>();

				for (VtSprint vtSprint : listaSprints) {
					esSprintItems.add(new SelectItem(vtSprint.getSpriCodigo(), vtSprint.getNombre()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esSprintItems;
	}

	public void setEsSprintItems(List<SelectItem> esSprintItems) {
		this.esSprintItems = esSprintItems;
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

	public void listener_txtFechaFin() {
		Date inputDate = (Date) txtFechaFin.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public void listener_txtFechaInicio() {
		Date inputDate = (Date) txtFechaInicio.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
	}

	public CommandButton getBtnCrearS() {
		return btnCrearS;
	}

	public void setBtnCrearS(CommandButton btnCrearS) {
		this.btnCrearS = btnCrearS;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnLimpiarS() {
		return btnLimpiarS;
	}

	public void setBtnLimpiarS(CommandButton btnLimpiarS) {
		this.btnLimpiarS = btnLimpiarS;
	}

	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public VtArtefacto getEntity() {
		return entity;
	}

	public void setEntity(VtArtefacto entity) {
		this.entity = entity;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	
	public SelectOneMenu getSomPilaProducto() {
		return somPilaProducto;
	}

	public void setSomPilaProducto(SelectOneMenu somPilaProducto) {
		this.somPilaProducto = somPilaProducto;
	}

	public List<SelectItem> getEsPilaProductoItems() {
		
		try{
			if(esPilaProductoItems==null){
				List<VtPilaProducto> listaPilasProducto=businessDelegatorView.getVtPilaProducto();
				esPilaProductoItems=new ArrayList<SelectItem>();
				for (VtPilaProducto vtPilaProducto:listaPilasProducto) {
					esPilaProductoItems.add(new SelectItem(vtPilaProducto.getPilaCodigo(),vtPilaProducto.getNombre()));
				}
			}

		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return esPilaProductoItems;
	}

	public void setEsPilaProductoItems(List<SelectItem> esPilaProductoItems) {
		this.esPilaProductoItems = esPilaProductoItems;
	}


	public String crearArtefacto() {
		log.info("Creando artefacto");

		try {

			VtArtefacto vtArtefacto = new VtArtefacto();

			vtArtefacto.setDescripcion(txtdescripcion.getValue().toString().trim());
			vtArtefacto.setTitulo(txtnombre.getValue().toString().trim());
			Date fechaCreacion = new Date();
			vtArtefacto.setFechaCreacion(fechaCreacion);

			int esfuerzo = Integer.parseInt(txtEsfuerzoEstimado.getValue().toString().trim());
			vtArtefacto.setEsfuerzoEstimado(esfuerzo);
			
			String  pilasProducto = somPilaProducto.getValue().toString().trim();
			Long idPilaProducto = Long.parseLong(pilasProducto);
			VtPilaProducto vtPilaProducto = businessDelegatorView.getVtPilaProducto(idPilaProducto);
			vtArtefacto.setVtPilaProducto(vtPilaProducto);
			
			String artefacto = somTiposDeArtefactos.getValue().toString().trim();
			Long idTipoArtefacto= Long.parseLong(artefacto);
			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(idTipoArtefacto);
			vtArtefacto.setVtTipoArtefacto(vtTipoArtefacto);

			String estados = somEstados.getValue().toString().trim();
			Long estadoArtefacto = Long.parseLong(estados);
			VtEstado vtEstado = businessDelegatorView.getVtEstado(estadoArtefacto);
			vtArtefacto.setVtEstado(vtEstado);

			String spring  = somSprints.getValue().toString().toString();
			Long idSpring = Long.parseLong(spring);
			VtSprint vtSprint = businessDelegatorView.getVtSprint(idSpring);
			vtArtefacto.setVtSprint(vtSprint);
			
			String tipoPri = somPrioridades.getValue().toString().trim();
			Long tipoPrioridad = Long.parseLong(tipoPri);
			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(tipoPrioridad);
			vtArtefacto.setVtPrioridad(vtPrioridad);

			VtUsuario vtUsuario = (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			vtArtefacto.setUsuCreador(vtUsuario.getUsuaCodigo());

			String activo = somActivo.getValue().toString().trim();
			if (activo.equalsIgnoreCase("Si")) {
				vtArtefacto.setActivo("S");
			} else {
				vtArtefacto.setActivo("N");
			}

			businessDelegatorView.saveVtArtefacto(vtArtefacto);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El artefacto se creó con exito"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
		return "";
	}

}
