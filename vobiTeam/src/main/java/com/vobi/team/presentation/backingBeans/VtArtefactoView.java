package com.vobi.team.presentation.backingBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vobi.team.modelo.VtArchivo;
import com.vobi.team.modelo.VtArtefacto;
import com.vobi.team.modelo.VtEmpresa;
import com.vobi.team.modelo.VtEstado;
import com.vobi.team.modelo.VtPilaProducto;
import com.vobi.team.modelo.VtPrioridad;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtSprint;
import com.vobi.team.modelo.VtTipoArtefacto;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.dto.VtArchivoDTO;
import com.vobi.team.modelo.dto.VtArtefactoDTO;
import com.vobi.team.modelo.dto.VtSprintDTO;
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
	private SelectOneMenu somSprintsCrear;
	private SelectOneMenu somPilaProductoCrear;
	private SelectOneMenu somSprints;
	private SelectOneMenu somPilaProducto;
	private SelectOneMenu somUsuariosArtefactos;
	private SelectOneMenu somEmpresas;
	private SelectOneMenu somProyectos;
	private SelectOneMenu somTiposDeArtefactosCambio;
	private SelectOneMenu somPilaProductoCambio;
	private SelectOneMenu somSprintsCambio;
	private SelectOneMenu somActivoCambio;
	private SelectOneMenu somEstadosCambio;
	private SelectOneMenu somPrioridadesCambio;
	private List<SelectItem> esUsuarioArtefactoItems;
	private List<SelectItem> esPilaProductoItems;
	private List<SelectItem> esActivoItems;
	private List<SelectItem> esPrioridadItems;
	private List<SelectItem> esTipoArtefactoItems;
	private List<SelectItem> esEstadoItems;
	private List<SelectItem> esSprintItems;
	private List<SelectItem> lasEmpresasItems;
	private List<SelectItem> losProyectosFiltro;
	private List<SelectItem> lasPilasDeProductoFiltro;
	private List<VtArtefactoDTO> data;
	private List<VtArtefactoDTO> dataFiltro;
	private VtArchivoDTO selectedVtArchivo;
	private List<VtArtefactoDTO> dataFiltroI;
	private List<VtSprintDTO> dataSprint;
	private VtArtefactoDTO selectedVtArtefacto;
	private InputTextarea txtdescripcion;
	private InputText txtnombre;
	private InputText txtEsfuerzoEstimado;
	private Calendar txtFechaFin;
	private Calendar txtFechaInicio;
	private CommandButton btnCrearS;
	private CommandButton btnCrear;
	private CommandButton btnLimpiarS;
	private CommandButton btnGuardar;

	private Long spriCodigo = null;

	private CommandButton btnLimpiar;
	private CommandButton btnFiltrar;
	private VtArtefacto entity;
	private VtArchivo archivoEntity;

	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public InputTextarea getTxtdescripcion() {
		return txtdescripcion;
	}
	public VtArchivo getArchivoEntity() {
		return archivoEntity;
	}

	public void setArchivoEntity(VtArchivo archivoEntity) {
		this.archivoEntity = archivoEntity;
	}

	public SelectOneMenu getSomUsuariosArtefactos() {
		return somUsuariosArtefactos;
	}

	public VtArtefactoDTO getSelectedVtArtefacto() {
		return selectedVtArtefacto;
	}

	public Long getSpriCodigo() {
		return spriCodigo;
	}
	public void setSpriCodigo(Long spriCodigo) {
		this.spriCodigo = spriCodigo;
	}
	public CommandButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(CommandButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public void setSelectedVtArtefacto(VtArtefactoDTO selectedVtArtefacto) {
		this.selectedVtArtefacto = selectedVtArtefacto;
	}

	public void setSomUsuariosArtefactos(SelectOneMenu somUsuariosArtefactos) {
		this.somUsuariosArtefactos = somUsuariosArtefactos;
	}

	public List<VtSprintDTO> getDataSprint() {
		return dataSprint;
	}

	public void setDataSprint(List<VtSprintDTO> dataSprint) {
		this.dataSprint = dataSprint;
	}

	public List<SelectItem> getEsUsuarioArtefactoItems() {
		return esUsuarioArtefactoItems;
	}

	public List<SelectItem> getLasEmpresasItems() {
		try {
			if (lasEmpresasItems == null) {
				List<VtEmpresa> listaEmpresas = businessDelegatorView.getVtEmpresa();
				lasEmpresasItems = new ArrayList<SelectItem>();
				for (VtEmpresa vtEmpresa : listaEmpresas) {
					if (vtEmpresa.getActivo().equalsIgnoreCase("S")) {
						lasEmpresasItems.add(new SelectItem(vtEmpresa.getEmprCodigo(), vtEmpresa.getNombre()));
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return lasEmpresasItems;
	}

	public void setLasEmpresasItems(List<SelectItem> lasEmpresasItems) {
		this.lasEmpresasItems = lasEmpresasItems;
	}

	public void setEsUsuarioArtefactoItems(List<SelectItem> esUsuarioArtefactoItems) {
		this.esUsuarioArtefactoItems = esUsuarioArtefactoItems;
	}

	public List<SelectItem> getLosProyectosFiltro() {
		return losProyectosFiltro;
	}

	public void setLosProyectosFiltro(List<SelectItem> losProyectosFiltro) {
		this.losProyectosFiltro = losProyectosFiltro;
	}

	public List<SelectItem> getLasPilasDeProductoFiltro() {
		return lasPilasDeProductoFiltro;
	}

	public void setLasPilasDeProductoFiltro(List<SelectItem> lasPilasDeProductoFiltro) {
		this.lasPilasDeProductoFiltro = lasPilasDeProductoFiltro;
	}

	public CommandButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public SelectOneMenu getSomSprintsCrear() {
		return somSprintsCrear;
	}

	public void setSomSprintsCrear(SelectOneMenu somSprintsCrear) {
		this.somSprintsCrear = somSprintsCrear;
	}

	public SelectOneMenu getSomPilaProductoCrear() {
		return somPilaProductoCrear;
	}

	public void setSomPilaProductoCrear(SelectOneMenu somPilaProductoCrear) {
		this.somPilaProductoCrear = somPilaProductoCrear;
	}

	public SelectOneMenu getSomEmpresas() {
		return somEmpresas;
	}

	public void setSomEmpresas(SelectOneMenu somEmpresas) {
		this.somEmpresas = somEmpresas;
	}

	public SelectOneMenu getSomProyectos() {
		return somProyectos;
	}

	public void setSomProyectos(SelectOneMenu somProyectos) {
		this.somProyectos = somProyectos;
	}

	public void setBtnFiltrar(CommandButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
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

	public List<VtArtefactoDTO> getData() {
		return data;
	}

	public void setData(List<VtArtefactoDTO> data) {
		this.data = data;
	}

	public List<VtArtefactoDTO> getDataFiltro() {
		return dataFiltro;
	}

	public void setDataFiltro(List<VtArtefactoDTO> dataFiltro) {
		this.dataFiltro = dataFiltro;
	}

	public List<VtArtefactoDTO> getDataFiltroI() {
		return dataFiltroI;
	}

	public void setDataFiltroI(List<VtArtefactoDTO> dataFiltroI) {
		this.dataFiltroI = dataFiltroI;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public List<SelectItem> getEsPilaProductoItems() {

		try {
			if (esPilaProductoItems == null) {
				List<VtPilaProducto> listaPilasProducto = businessDelegatorView.getVtPilaProducto();
				esPilaProductoItems = new ArrayList<SelectItem>();
				for (VtPilaProducto vtPilaProducto : listaPilasProducto) {
					esPilaProductoItems.add(new SelectItem(vtPilaProducto.getPilaCodigo(), vtPilaProducto.getNombre()));
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esPilaProductoItems;
	}

	public void setEsPilaProductoItems(List<SelectItem> esPilaProductoItems) {
		this.esPilaProductoItems = esPilaProductoItems;
	}

	public SelectOneMenu getSomTiposDeArtefactosCambio() {
		return somTiposDeArtefactosCambio;
	}

	public void setSomTiposDeArtefactosCambio(SelectOneMenu somTiposDeArtefactosCambio) {
		this.somTiposDeArtefactosCambio = somTiposDeArtefactosCambio;
	}

	public SelectOneMenu getSomPilaProductoCambio() {
		return somPilaProductoCambio;
	}

	public void setSomPilaProductoCambio(SelectOneMenu somPilaProductoCambio) {
		this.somPilaProductoCambio = somPilaProductoCambio;
	}

	public SelectOneMenu getSomSprintsCambio() {
		return somSprintsCambio;
	}

	public void setSomSprintsCambio(SelectOneMenu somSprintsCambio) {
		this.somSprintsCambio = somSprintsCambio;
	}

	public SelectOneMenu getSomActivoCambio() {
		return somActivoCambio;
	}

	public void setSomActivoCambio(SelectOneMenu somActivoCambio) {
		this.somActivoCambio = somActivoCambio;
	}

	public SelectOneMenu getSomEstadosCambio() {
		return somEstadosCambio;
	}

	public void setSomEstadosCambio(SelectOneMenu somEstadosCambio) {
		this.somEstadosCambio = somEstadosCambio;
	}

	public SelectOneMenu getSomPrioridadesCambio() {
		return somPrioridadesCambio;
	}

	public void setSomPrioridadesCambio(SelectOneMenu somPrioridadesCambio) {
		this.somPrioridadesCambio = somPrioridadesCambio;
	}

	public String crearArtefacto(){
		log.info("Creando artefacto");

		try {

			entity = new VtArtefacto();

			entity.setDescripcion(txtdescripcion.getValue().toString().trim());
			entity.setTitulo(txtnombre.getValue().toString().trim());
			Date fechaCreacion = new Date();
			entity.setFechaCreacion(fechaCreacion);

			int esfuerzo = Integer.parseInt(txtEsfuerzoEstimado.getValue().toString().trim());
			entity.setEsfuerzoEstimado(esfuerzo);

			String pilasProducto = somPilaProductoCrear.getValue().toString().trim();
			Long idPilaProducto = Long.parseLong(pilasProducto);
			VtPilaProducto vtPilaProducto = businessDelegatorView.getVtPilaProducto(idPilaProducto);
			entity.setVtPilaProducto(vtPilaProducto);

			String artefacto = somTiposDeArtefactos.getValue().toString().trim();
			Long idTipoArtefacto = Long.parseLong(artefacto);
			VtTipoArtefacto vtTipoArtefacto = businessDelegatorView.getVtTipoArtefacto(idTipoArtefacto);
			entity.setVtTipoArtefacto(vtTipoArtefacto);

			String estados = somEstados.getValue().toString().trim();
			Long estadoArtefacto = Long.parseLong(estados);
			VtEstado vtEstado = businessDelegatorView.getVtEstado(estadoArtefacto);
			entity.setVtEstado(vtEstado);

			String spring = somSprintsCrear.getValue().toString().toString();
			Long idSpring = Long.parseLong(spring);
			VtSprint vtSprint = businessDelegatorView.getVtSprint(idSpring);
			entity.setVtSprint(vtSprint);

			String tipoPri = somPrioridades.getValue().toString().trim();
			Long tipoPrioridad = Long.parseLong(tipoPri);
			VtPrioridad vtPrioridad = businessDelegatorView.getVtPrioridad(tipoPrioridad);
			entity.setVtPrioridad(vtPrioridad);

			VtUsuario vtUsuario = (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			entity.setUsuCreador(vtUsuario.getUsuaCodigo());

			String activo = somActivo.getValue().toString().trim();
			if (activo.equalsIgnoreCase("Si")) {
				entity.setActivo("S");
			} else {
				entity.setActivo("N");
			}

			businessDelegatorView.saveVtArtefacto(entity);

			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El artefacto se creó con exito"));		

			limpiar();
			action_clear();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getMessage()));
		}
		return "";
	}


	public String handleFileUpload(FileUploadEvent event) throws IOException {
		try {
			//			selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent().getAttributes()
			//                    .get("selectedVtProyecto"));
			//			Long artefactoId = new Long(selectedVtArtefacto.getArteCodigo());
			//			entity=businessDelegatorView.getVtArtefacto(artefactoId);
			VtUsuario vtUsuarioSession = (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			VtArchivo vtArchivo = new VtArchivo();
			vtArchivo.setNombre(event.getFile().getFileName());
			vtArchivo.setFechaCreacion(new Date());
			vtArchivo.setFechaModificacion(new Date());
			vtArchivo.setUsuCreador(vtUsuarioSession.getUsuaCodigo());
			vtArchivo.setUsuModificador(vtUsuarioSession.getUsuaCodigo());
			vtArchivo.setActivo("S");
			vtArchivo.setArchivo(event.getFile().getContents());
			vtArchivo.setVtArtefacto(entity);

			FacesUtils.addInfoMessage("Ok",
					"Fichero " + event.getFile().getFileName() + " subido correctamente.");
			businessDelegatorView.saveVtArchivo(vtArchivo);

		} catch (Exception e) {
			FacesUtils.addInfoMessage(e.getMessage());
		}
		return "";
	}

	public void FileDownloadView(ActionEvent evt){
		try {
			setSelectedVtArchivo((VtArchivoDTO)(evt.getComponent().getAttributes()
					.get("selectedVtArchivo")));
			Long archivoId = new Long(selectedVtArtefacto.getArteCodigo());
			archivoEntity=businessDelegatorView.getVtArchivo(archivoId);
			byte[] archivo = archivoEntity.getArchivo();
			InputStream inputStream = new ByteArrayInputStream(archivo);
			archivos = new DefaultStreamedContent(inputStream, null, archivoEntity.getNombre());
			FacesUtils.addInfoMessage("Archivo descargado correctamente");

		} catch (Exception e) {
			FacesUtils.addInfoMessage("Falla al descargar el archivo");
		}
	}
	private DefaultStreamedContent archivos;

	public DefaultStreamedContent getArchivo() {
		return archivos;
	}

	public void setArchivo(DefaultStreamedContent archivo) {
		this.archivos = archivo;
	}

	public String limpiar(){
		somActivo.setValue("-1");
		txtdescripcion.resetValue();
		txtEsfuerzoEstimado.resetValue();
		txtnombre.resetValue();
		somEstados.setValue("-1");
		somPilaProductoCrear.setValue("-1");
		somPrioridades.setValue("-1");
		somSprintsCrear.setValue("-1");
		somTiposDeArtefactos.setValue("-1");
		return "";
	}

	public String filtrar(ValueChangeEvent evt) {
		try {

			String sprint = evt.getNewValue().toString();
			log.info("Código del sprint " + sprint);
			spriCodigo = Long.valueOf(sprint);
			VtSprint vtSprint = businessDelegatorView.getVtSprint(spriCodigo);
			dataFiltro = businessDelegatorView.getDataVtArtefactoFiltro(vtSprint.getSpriCodigo());
			dataFiltroI = businessDelegatorView.getDataVtArtefactoFiltroI(vtSprint.getSpriCodigo());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();
		return "";
	}

	public String modificar(ActionEvent evt) {
		selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent().getAttributes().get("selectedVtArtefacto"));

		txtdescripcion.setValue(selectedVtArtefacto.getDescripcion());
		txtdescripcion.setDisabled(false);
		txtnombre.setValue(selectedVtArtefacto.getTitulo());
		txtnombre.setDisabled(false);
		txtEsfuerzoEstimado.setValue(selectedVtArtefacto.getEsfuerzoEstimado());
		txtEsfuerzoEstimado.setDisabled(false);
		somTiposDeArtefactos.setValue(selectedVtArtefacto.getTparCodigo_VtTipoArtefacto());
		somTiposDeArtefactos.setDisabled(false);
		somPilaProductoCrear.setValue(selectedVtArtefacto.getPilaCodigo_VtPilaProducto());
		somPilaProductoCrear.setDisabled(false);
		somSprintsCrear.setValue(selectedVtArtefacto.getSpriCodigo_VtSprint());
		somSprintsCrear.setDisabled(false);
		somActivo.setValue(selectedVtArtefacto.getActivo());
		somActivo.setDisabled(false);
		somPrioridades.setValue(selectedVtArtefacto.getPrioCodigo_VtPrioridad());
		somPrioridades.setDisabled(false);
		somEstados.setValue(selectedVtArtefacto.getEstaCodigo_VtEstado());
		somEstados.setDisabled(false);
		btnGuardar.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedVtArtefacto == null) && (entity == null)) {
			} else {
				action_modify();
			}
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_clear(){
		somActivo.setValue("-1");
		txtdescripcion.resetValue();
		txtEsfuerzoEstimado.resetValue();
		txtnombre.resetValue();
		somEstados.setValue("-1");
		somPilaProductoCrear.setValue("-1");
		somPrioridades.setValue("-1");
		somSprintsCrear.setValue("-1");
		somTiposDeArtefactos.setValue("-1");

		return "";
	}
	public String filtrarEmpresa(ValueChangeEvent evt) {
		try {
			VtEmpresa vtEmpresa = null;
			losProyectosFiltro = null;
			String empresaS = somEmpresas.getValue().toString().trim();
			if (empresaS.isEmpty() || empresaS.equals("-1")) {
			} else {
				Long empresa = Long.parseLong(empresaS);
				vtEmpresa = businessDelegatorView.getVtEmpresa(empresa);
			}
			try {
				if (losProyectosFiltro == null) {
					List<VtProyecto> listaProyectos = businessDelegatorView.getVtProyecto();
					losProyectosFiltro = new ArrayList<SelectItem>();
					for (VtProyecto vtProyecto : listaProyectos) {
						if (vtProyecto.getActivo().equalsIgnoreCase("S")
								&& vtProyecto.getVtEmpresa().getEmprCodigo().equals(vtEmpresa.getEmprCodigo())) {
							losProyectosFiltro.add(new SelectItem(vtProyecto.getProyCodigo(), vtProyecto.getNombre()));
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "";
	}

	public String filtrarProyecto(ValueChangeEvent evt) {
		try {
			VtProyecto vtProyecto = null;
			lasPilasDeProductoFiltro = null;
			String proyectoS = somProyectos.getValue().toString().trim();

			if (proyectoS.isEmpty() || proyectoS.equals("-1")) {
			} else {
				Long proyecto = Long.parseLong(proyectoS);
				vtProyecto = businessDelegatorView.getVtProyecto(proyecto);
			}

			try {
				if (lasPilasDeProductoFiltro == null) {
					List<VtPilaProducto> listaPilasDeProducto = businessDelegatorView.getVtPilaProducto();
					lasPilasDeProductoFiltro = new ArrayList<SelectItem>();
					for (VtPilaProducto vtPilaProducto : listaPilasDeProducto) {
						if (vtPilaProducto.getActivo().equalsIgnoreCase("S")
								&& vtPilaProducto.getVtProyecto().getProyCodigo().equals(vtProyecto.getProyCodigo())) {
							lasPilasDeProductoFiltro
							.add(new SelectItem(vtPilaProducto.getPilaCodigo(), vtPilaProducto.getNombre()));
						}
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}

	public String imprimirValue(ValueChangeEvent vce) {

		try {
			VtPilaProducto vtPilaProducto = null;
			String pila = vce.getNewValue().toString();
			log.info("Información :" + pila);
			if (pila.isEmpty() || pila.equals("-1")) {
			} else {
				Long idPila = Long.parseLong(pila);
				vtPilaProducto = businessDelegatorView.getVtPilaProducto(idPila);
			}

			try {

				dataSprint = businessDelegatorView.getDataVtSprintFiltro(vtPilaProducto.getPilaCodigo());
				esSprintItems = new ArrayList<SelectItem>();
				for (VtSprintDTO vtSprintDTO : dataSprint) {
					esSprintItems.add(new SelectItem(vtSprintDTO.getSpriCodigo(), vtSprintDTO.getNombre()));
				}

			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {				
				Long artefactoCodigo = new Long(selectedVtArtefacto.getArteCodigo());
				entity = businessDelegatorView.getVtArtefacto(artefactoCodigo);
			}

			String activo = somActivoCambio.getValue().toString().trim();
			if (activo.equalsIgnoreCase("Si")) {
				entity.setActivo("S");
			} else {
				if(activo.equals("-1")){
					entity.setActivo(entity.getActivo());
				}
				else{
					entity.setActivo("N");
				}
			}
			entity.setTitulo(FacesUtils.checkString(txtnombre));
			entity.setDescripcion(FacesUtils.checkString(txtdescripcion));
			Date fechaModificacion = new Date();
			entity.setFechaModificacion(fechaModificacion);

			VtUsuario vtUsuarioEnSession =  (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			entity.setUsuModificador(vtUsuarioEnSession.getUsuaCodigo());
			businessDelegatorView.updateVtArtefacto(entity);

			String artefacto = somSprints.getValue().toString().trim();
			Long codigoArtefacto =Long.valueOf(artefacto);
			VtArtefacto vtArtefacto = businessDelegatorView.getVtArtefacto(codigoArtefacto);
			dataFiltro = businessDelegatorView.getDataVtArtefactoFiltro(vtArtefacto.getArteCodigo());
			dataFiltroI = businessDelegatorView.getDataVtArtefactoFiltroI(vtArtefacto.getArteCodigo());

			FacesUtils.addInfoMessage("El Artefacto se ha sido modificado con exito");
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public VtArchivoDTO getSelectedVtArchivo() {
		return selectedVtArchivo;
	}

	public void setSelectedVtArchivo(VtArchivoDTO selectedVtArchivo) {
		this.selectedVtArchivo = selectedVtArchivo;
	}

	public String cambiarEstado(ActionEvent evt){

		selectedVtArtefacto = (VtArtefactoDTO) (evt.getComponent().getAttributes()
				.get("selectedVtArtefacto"));

		try {
			if (entity == null) {
				Long arteCodigo = new Long(selectedVtArtefacto.getArteCodigo());
				entity = businessDelegatorView.getVtArtefacto(arteCodigo);
			}

			String cambioActivo=entity.getActivo().toString().trim();
			if (cambioActivo.equalsIgnoreCase("S")) {
				entity.setActivo("N");
			}else{
				entity.setActivo("S");
			}	
			
			log.info("Codigo del artefacto que le entro" + getSpriCodigo());
			Long sprintCodigo = Long.valueOf(getSpriCodigo());
			VtSprint vtSprint = businessDelegatorView.getVtSprint(sprintCodigo);

			Date fechaModificacion= new Date();
			entity.setFechaModificacion(fechaModificacion);

			VtUsuario vtUsuarioEnSession =  (VtUsuario) FacesUtils.getfromSession("vtUsuario");
			entity.setUsuModificador(vtUsuarioEnSession.getUsuaCodigo());

			businessDelegatorView.updateVtArtefacto(entity);

			FacesContext.getCurrentInstance().
			addMessage("", new FacesMessage("El artefacto se modificó con exito"));

			dataFiltro=businessDelegatorView.getDataVtArtefactoFiltro(vtSprint.getSpriCodigo());
			dataFiltroI=businessDelegatorView.getDataVtArtefactoFiltroI(vtSprint.getSpriCodigo());

			selectedVtArtefacto=null;
			entity=null;

		}catch (Exception e) {
			data = null;
			log.error(e.toString());
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

}
