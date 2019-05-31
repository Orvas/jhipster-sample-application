package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.domain.Valve;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListValveType;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListValveManufacturer;
import io.github.jhipster.application.domain.ListValveSpecification;
import io.github.jhipster.application.domain.ListValveFunction;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.ValveHistRepository;
import io.github.jhipster.application.service.ValveHistService;
import io.github.jhipster.application.service.dto.ValveHistDTO;
import io.github.jhipster.application.service.mapper.ValveHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ValveHistCriteria;
import io.github.jhipster.application.service.ValveHistQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ValveHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ValveHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUM = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIAMETER_OUTER_STEEL = 1;
    private static final Integer UPDATED_DIAMETER_OUTER_STEEL = 2;

    private static final BigDecimal DEFAULT_INTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_INTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_EXTERNAL_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_EXTERNAL_COAT_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CONCR_COATING = 1;
    private static final Integer UPDATED_IS_CONCR_COATING = 2;

    private static final BigDecimal DEFAULT_CONCR_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONCR_COAT_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONCR_COAT_DENSITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MEAS_WALL_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_MEAS_WALL_THICKNESS = new BigDecimal(2);

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final BigDecimal DEFAULT_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMTS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMTS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SMYS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SMYS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDBM = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDBM = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDAF = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDAF = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SDCS = new BigDecimal(1);
    private static final BigDecimal UPDATED_SDCS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ALLOW_TENS_STRAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_ALLOW_TENS_STRAIN = new BigDecimal(2);

    private static final Integer DEFAULT_CORROSION_ALLOWANCE = 1;
    private static final Integer UPDATED_CORROSION_ALLOWANCE = 2;

    private static final Integer DEFAULT_FABRICATION_ALLOWANCE = 1;
    private static final Integer UPDATED_FABRICATION_ALLOWANCE = 2;

    private static final LocalDate DEFAULT_DATE_MANUFACTURED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MANUFACTURED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MILL_TEST_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MILL_TEST_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE_IN = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE_IN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE_OUT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE_OUT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INCIDENTAL_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_INCIDENTAL_PRESSURE = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_INSTALLED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_INSTALLED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_SEAM_ORIENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ORIENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAM_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAM_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AZIMUTH = new BigDecimal(1);
    private static final BigDecimal UPDATED_AZIMUTH = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SEAB_INSTALL_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_SEAB_INSTALL_TEMP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_VERTICAL_ANGLE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VERTICAL_ANGLE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEAT_TREAT_DURAT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEAT_TREAT_DURAT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAX_DESIGN_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAX_DESIGN_TEMP = new BigDecimal(2);

    private static final String DEFAULT_HEAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HEAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DESIGN_COORD = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_COORD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_INST = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_INST = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private ValveHistRepository valveHistRepository;

    @Autowired
    private ValveHistMapper valveHistMapper;

    @Autowired
    private ValveHistService valveHistService;

    @Autowired
    private ValveHistQueryService valveHistQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restValveHistMockMvc;

    private ValveHist valveHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ValveHistResource valveHistResource = new ValveHistResource(valveHistService, valveHistQueryService);
        this.restValveHistMockMvc = MockMvcBuilders.standaloneSetup(valveHistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValveHist createEntity(EntityManager em) {
        ValveHist valveHist = new ValveHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .name(DEFAULT_NAME)
            .serialNum(DEFAULT_SERIAL_NUM)
            .model(DEFAULT_MODEL)
            .diameterOuterSteel(DEFAULT_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(DEFAULT_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(DEFAULT_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(DEFAULT_IS_CONCR_COATING)
            .concrCoatThickness(DEFAULT_CONCR_COAT_THICKNESS)
            .concrCoatDensity(DEFAULT_CONCR_COAT_DENSITY)
            .measWallThickness(DEFAULT_MEAS_WALL_THICKNESS)
            .length(DEFAULT_LENGTH)
            .weight(DEFAULT_WEIGHT)
            .smts(DEFAULT_SMTS)
            .smys(DEFAULT_SMYS)
            .sdbm(DEFAULT_SDBM)
            .sdaf(DEFAULT_SDAF)
            .sdcs(DEFAULT_SDCS)
            .allowTensStrain(DEFAULT_ALLOW_TENS_STRAIN)
            .corrosionAllowance(DEFAULT_CORROSION_ALLOWANCE)
            .fabricationAllowance(DEFAULT_FABRICATION_ALLOWANCE)
            .dateManufactured(DEFAULT_DATE_MANUFACTURED)
            .millTestPressure(DEFAULT_MILL_TEST_PRESSURE)
            .designPressure(DEFAULT_DESIGN_PRESSURE)
            .designPressureIn(DEFAULT_DESIGN_PRESSURE_IN)
            .designPressureOut(DEFAULT_DESIGN_PRESSURE_OUT)
            .incidentalPressure(DEFAULT_INCIDENTAL_PRESSURE)
            .dateInstalled(DEFAULT_DATE_INSTALLED)
            .seamOrient(DEFAULT_SEAM_ORIENT)
            .seamAngle(DEFAULT_SEAM_ANGLE)
            .azimuth(DEFAULT_AZIMUTH)
            .seabInstallTemp(DEFAULT_SEAB_INSTALL_TEMP)
            .verticalAngle(DEFAULT_VERTICAL_ANGLE)
            .heatTreatDurat(DEFAULT_HEAT_TREAT_DURAT)
            .maxDesignTemp(DEFAULT_MAX_DESIGN_TEMP)
            .heatNumber(DEFAULT_HEAT_NUMBER)
            .coord(DEFAULT_COORD)
            .designCoord(DEFAULT_DESIGN_COORD)
            .kpInst(DEFAULT_KP_INST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        valveHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        valveHist.setIdStatus(listObjectStatus);
        return valveHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ValveHist createUpdatedEntity(EntityManager em) {
        ValveHist valveHist = new ValveHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .name(UPDATED_NAME)
            .serialNum(UPDATED_SERIAL_NUM)
            .model(UPDATED_MODEL)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measWallThickness(UPDATED_MEAS_WALL_THICKNESS)
            .length(UPDATED_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .designPressureIn(UPDATED_DESIGN_PRESSURE_IN)
            .designPressureOut(UPDATED_DESIGN_PRESSURE_OUT)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        valveHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        valveHist.setIdStatus(listObjectStatus);
        return valveHist;
    }

    @BeforeEach
    public void initTest() {
        valveHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createValveHist() throws Exception {
        int databaseSizeBeforeCreate = valveHistRepository.findAll().size();

        // Create the ValveHist
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);
        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isCreated());

        // Validate the ValveHist in the database
        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeCreate + 1);
        ValveHist testValveHist = valveHistList.get(valveHistList.size() - 1);
        assertThat(testValveHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testValveHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testValveHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testValveHist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testValveHist.getSerialNum()).isEqualTo(DEFAULT_SERIAL_NUM);
        assertThat(testValveHist.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testValveHist.getDiameterOuterSteel()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL);
        assertThat(testValveHist.getInternalCoatThickness()).isEqualTo(DEFAULT_INTERNAL_COAT_THICKNESS);
        assertThat(testValveHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testValveHist.getIsConcrCoating()).isEqualTo(DEFAULT_IS_CONCR_COATING);
        assertThat(testValveHist.getConcrCoatThickness()).isEqualTo(DEFAULT_CONCR_COAT_THICKNESS);
        assertThat(testValveHist.getConcrCoatDensity()).isEqualTo(DEFAULT_CONCR_COAT_DENSITY);
        assertThat(testValveHist.getMeasWallThickness()).isEqualTo(DEFAULT_MEAS_WALL_THICKNESS);
        assertThat(testValveHist.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testValveHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testValveHist.getSmts()).isEqualTo(DEFAULT_SMTS);
        assertThat(testValveHist.getSmys()).isEqualTo(DEFAULT_SMYS);
        assertThat(testValveHist.getSdbm()).isEqualTo(DEFAULT_SDBM);
        assertThat(testValveHist.getSdaf()).isEqualTo(DEFAULT_SDAF);
        assertThat(testValveHist.getSdcs()).isEqualTo(DEFAULT_SDCS);
        assertThat(testValveHist.getAllowTensStrain()).isEqualTo(DEFAULT_ALLOW_TENS_STRAIN);
        assertThat(testValveHist.getCorrosionAllowance()).isEqualTo(DEFAULT_CORROSION_ALLOWANCE);
        assertThat(testValveHist.getFabricationAllowance()).isEqualTo(DEFAULT_FABRICATION_ALLOWANCE);
        assertThat(testValveHist.getDateManufactured()).isEqualTo(DEFAULT_DATE_MANUFACTURED);
        assertThat(testValveHist.getMillTestPressure()).isEqualTo(DEFAULT_MILL_TEST_PRESSURE);
        assertThat(testValveHist.getDesignPressure()).isEqualTo(DEFAULT_DESIGN_PRESSURE);
        assertThat(testValveHist.getDesignPressureIn()).isEqualTo(DEFAULT_DESIGN_PRESSURE_IN);
        assertThat(testValveHist.getDesignPressureOut()).isEqualTo(DEFAULT_DESIGN_PRESSURE_OUT);
        assertThat(testValveHist.getIncidentalPressure()).isEqualTo(DEFAULT_INCIDENTAL_PRESSURE);
        assertThat(testValveHist.getDateInstalled()).isEqualTo(DEFAULT_DATE_INSTALLED);
        assertThat(testValveHist.getSeamOrient()).isEqualTo(DEFAULT_SEAM_ORIENT);
        assertThat(testValveHist.getSeamAngle()).isEqualTo(DEFAULT_SEAM_ANGLE);
        assertThat(testValveHist.getAzimuth()).isEqualTo(DEFAULT_AZIMUTH);
        assertThat(testValveHist.getSeabInstallTemp()).isEqualTo(DEFAULT_SEAB_INSTALL_TEMP);
        assertThat(testValveHist.getVerticalAngle()).isEqualTo(DEFAULT_VERTICAL_ANGLE);
        assertThat(testValveHist.getHeatTreatDurat()).isEqualTo(DEFAULT_HEAT_TREAT_DURAT);
        assertThat(testValveHist.getMaxDesignTemp()).isEqualTo(DEFAULT_MAX_DESIGN_TEMP);
        assertThat(testValveHist.getHeatNumber()).isEqualTo(DEFAULT_HEAT_NUMBER);
        assertThat(testValveHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testValveHist.getDesignCoord()).isEqualTo(DEFAULT_DESIGN_COORD);
        assertThat(testValveHist.getKpInst()).isEqualTo(DEFAULT_KP_INST);
        assertThat(testValveHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testValveHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testValveHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testValveHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testValveHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testValveHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testValveHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createValveHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = valveHistRepository.findAll().size();

        // Create the ValveHist with an existing ID
        valveHist.setId(1L);
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ValveHist in the database
        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = valveHistRepository.findAll().size();
        // set the field null
        valveHist.setDateFrom(null);

        // Create the ValveHist, which fails.
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = valveHistRepository.findAll().size();
        // set the field null
        valveHist.setDateTo(null);

        // Create the ValveHist, which fails.
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDiameterOuterSteelIsRequired() throws Exception {
        int databaseSizeBeforeTest = valveHistRepository.findAll().size();
        // set the field null
        valveHist.setDiameterOuterSteel(null);

        // Create the ValveHist, which fails.
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = valveHistRepository.findAll().size();
        // set the field null
        valveHist.setIsCurrentFlag(null);

        // Create the ValveHist, which fails.
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        restValveHistMockMvc.perform(post("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllValveHists() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList
        restValveHistMockMvc.perform(get("/api/valve-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valveHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNum").value(hasItem(DEFAULT_SERIAL_NUM.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measWallThickness").value(hasItem(DEFAULT_MEAS_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressureIn").value(hasItem(DEFAULT_DESIGN_PRESSURE_IN.intValue())))
            .andExpect(jsonPath("$.[*].designPressureOut").value(hasItem(DEFAULT_DESIGN_PRESSURE_OUT.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getValveHist() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get the valveHist
        restValveHistMockMvc.perform(get("/api/valve-hists/{id}", valveHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(valveHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.serialNum").value(DEFAULT_SERIAL_NUM.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.diameterOuterSteel").value(DEFAULT_DIAMETER_OUTER_STEEL))
            .andExpect(jsonPath("$.internalCoatThickness").value(DEFAULT_INTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.externalCoatThickness").value(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.isConcrCoating").value(DEFAULT_IS_CONCR_COATING))
            .andExpect(jsonPath("$.concrCoatThickness").value(DEFAULT_CONCR_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.concrCoatDensity").value(DEFAULT_CONCR_COAT_DENSITY.intValue()))
            .andExpect(jsonPath("$.measWallThickness").value(DEFAULT_MEAS_WALL_THICKNESS.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.smts").value(DEFAULT_SMTS.intValue()))
            .andExpect(jsonPath("$.smys").value(DEFAULT_SMYS.intValue()))
            .andExpect(jsonPath("$.sdbm").value(DEFAULT_SDBM.intValue()))
            .andExpect(jsonPath("$.sdaf").value(DEFAULT_SDAF.intValue()))
            .andExpect(jsonPath("$.sdcs").value(DEFAULT_SDCS.intValue()))
            .andExpect(jsonPath("$.allowTensStrain").value(DEFAULT_ALLOW_TENS_STRAIN.intValue()))
            .andExpect(jsonPath("$.corrosionAllowance").value(DEFAULT_CORROSION_ALLOWANCE))
            .andExpect(jsonPath("$.fabricationAllowance").value(DEFAULT_FABRICATION_ALLOWANCE))
            .andExpect(jsonPath("$.dateManufactured").value(DEFAULT_DATE_MANUFACTURED.toString()))
            .andExpect(jsonPath("$.millTestPressure").value(DEFAULT_MILL_TEST_PRESSURE.intValue()))
            .andExpect(jsonPath("$.designPressure").value(DEFAULT_DESIGN_PRESSURE.intValue()))
            .andExpect(jsonPath("$.designPressureIn").value(DEFAULT_DESIGN_PRESSURE_IN.intValue()))
            .andExpect(jsonPath("$.designPressureOut").value(DEFAULT_DESIGN_PRESSURE_OUT.intValue()))
            .andExpect(jsonPath("$.incidentalPressure").value(DEFAULT_INCIDENTAL_PRESSURE.intValue()))
            .andExpect(jsonPath("$.dateInstalled").value(DEFAULT_DATE_INSTALLED.toString()))
            .andExpect(jsonPath("$.seamOrient").value(DEFAULT_SEAM_ORIENT.intValue()))
            .andExpect(jsonPath("$.seamAngle").value(DEFAULT_SEAM_ANGLE.intValue()))
            .andExpect(jsonPath("$.azimuth").value(DEFAULT_AZIMUTH.intValue()))
            .andExpect(jsonPath("$.seabInstallTemp").value(DEFAULT_SEAB_INSTALL_TEMP.intValue()))
            .andExpect(jsonPath("$.verticalAngle").value(DEFAULT_VERTICAL_ANGLE.intValue()))
            .andExpect(jsonPath("$.heatTreatDurat").value(DEFAULT_HEAT_TREAT_DURAT.intValue()))
            .andExpect(jsonPath("$.maxDesignTemp").value(DEFAULT_MAX_DESIGN_TEMP.intValue()))
            .andExpect(jsonPath("$.heatNumber").value(DEFAULT_HEAT_NUMBER.toString()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.designCoord").value(DEFAULT_DESIGN_COORD.intValue()))
            .andExpect(jsonPath("$.kpInst").value(DEFAULT_KP_INST.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultValveHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the valveHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultValveHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultValveHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the valveHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultValveHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateFrom is not null
        defaultValveHistShouldBeFound("dateFrom.specified=true");

        // Get all the valveHistList where dateFrom is null
        defaultValveHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultValveHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the valveHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultValveHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultValveHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the valveHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultValveHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllValveHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateTo equals to DEFAULT_DATE_TO
        defaultValveHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the valveHistList where dateTo equals to UPDATED_DATE_TO
        defaultValveHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultValveHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the valveHistList where dateTo equals to UPDATED_DATE_TO
        defaultValveHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateTo is not null
        defaultValveHistShouldBeFound("dateTo.specified=true");

        // Get all the valveHistList where dateTo is null
        defaultValveHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultValveHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the valveHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultValveHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultValveHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the valveHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultValveHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where idWrk equals to DEFAULT_ID_WRK
        defaultValveHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the valveHistList where idWrk equals to UPDATED_ID_WRK
        defaultValveHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultValveHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the valveHistList where idWrk equals to UPDATED_ID_WRK
        defaultValveHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where idWrk is not null
        defaultValveHistShouldBeFound("idWrk.specified=true");

        // Get all the valveHistList where idWrk is null
        defaultValveHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultValveHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the valveHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultValveHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultValveHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the valveHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultValveHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllValveHistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where name equals to DEFAULT_NAME
        defaultValveHistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the valveHistList where name equals to UPDATED_NAME
        defaultValveHistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllValveHistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultValveHistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the valveHistList where name equals to UPDATED_NAME
        defaultValveHistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllValveHistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where name is not null
        defaultValveHistShouldBeFound("name.specified=true");

        // Get all the valveHistList where name is null
        defaultValveHistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySerialNumIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where serialNum equals to DEFAULT_SERIAL_NUM
        defaultValveHistShouldBeFound("serialNum.equals=" + DEFAULT_SERIAL_NUM);

        // Get all the valveHistList where serialNum equals to UPDATED_SERIAL_NUM
        defaultValveHistShouldNotBeFound("serialNum.equals=" + UPDATED_SERIAL_NUM);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySerialNumIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where serialNum in DEFAULT_SERIAL_NUM or UPDATED_SERIAL_NUM
        defaultValveHistShouldBeFound("serialNum.in=" + DEFAULT_SERIAL_NUM + "," + UPDATED_SERIAL_NUM);

        // Get all the valveHistList where serialNum equals to UPDATED_SERIAL_NUM
        defaultValveHistShouldNotBeFound("serialNum.in=" + UPDATED_SERIAL_NUM);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySerialNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where serialNum is not null
        defaultValveHistShouldBeFound("serialNum.specified=true");

        // Get all the valveHistList where serialNum is null
        defaultValveHistShouldNotBeFound("serialNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByModelIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where model equals to DEFAULT_MODEL
        defaultValveHistShouldBeFound("model.equals=" + DEFAULT_MODEL);

        // Get all the valveHistList where model equals to UPDATED_MODEL
        defaultValveHistShouldNotBeFound("model.equals=" + UPDATED_MODEL);
    }

    @Test
    @Transactional
    public void getAllValveHistsByModelIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where model in DEFAULT_MODEL or UPDATED_MODEL
        defaultValveHistShouldBeFound("model.in=" + DEFAULT_MODEL + "," + UPDATED_MODEL);

        // Get all the valveHistList where model equals to UPDATED_MODEL
        defaultValveHistShouldNotBeFound("model.in=" + UPDATED_MODEL);
    }

    @Test
    @Transactional
    public void getAllValveHistsByModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where model is not null
        defaultValveHistShouldBeFound("model.specified=true");

        // Get all the valveHistList where model is null
        defaultValveHistShouldNotBeFound("model.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDiameterOuterSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where diameterOuterSteel equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultValveHistShouldBeFound("diameterOuterSteel.equals=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the valveHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultValveHistShouldNotBeFound("diameterOuterSteel.equals=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDiameterOuterSteelIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where diameterOuterSteel in DEFAULT_DIAMETER_OUTER_STEEL or UPDATED_DIAMETER_OUTER_STEEL
        defaultValveHistShouldBeFound("diameterOuterSteel.in=" + DEFAULT_DIAMETER_OUTER_STEEL + "," + UPDATED_DIAMETER_OUTER_STEEL);

        // Get all the valveHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultValveHistShouldNotBeFound("diameterOuterSteel.in=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDiameterOuterSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where diameterOuterSteel is not null
        defaultValveHistShouldBeFound("diameterOuterSteel.specified=true");

        // Get all the valveHistList where diameterOuterSteel is null
        defaultValveHistShouldNotBeFound("diameterOuterSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDiameterOuterSteelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where diameterOuterSteel greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultValveHistShouldBeFound("diameterOuterSteel.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the valveHistList where diameterOuterSteel greater than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultValveHistShouldNotBeFound("diameterOuterSteel.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDiameterOuterSteelIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where diameterOuterSteel less than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultValveHistShouldNotBeFound("diameterOuterSteel.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the valveHistList where diameterOuterSteel less than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultValveHistShouldBeFound("diameterOuterSteel.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }


    @Test
    @Transactional
    public void getAllValveHistsByInternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where internalCoatThickness equals to DEFAULT_INTERNAL_COAT_THICKNESS
        defaultValveHistShouldBeFound("internalCoatThickness.equals=" + DEFAULT_INTERNAL_COAT_THICKNESS);

        // Get all the valveHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("internalCoatThickness.equals=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByInternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where internalCoatThickness in DEFAULT_INTERNAL_COAT_THICKNESS or UPDATED_INTERNAL_COAT_THICKNESS
        defaultValveHistShouldBeFound("internalCoatThickness.in=" + DEFAULT_INTERNAL_COAT_THICKNESS + "," + UPDATED_INTERNAL_COAT_THICKNESS);

        // Get all the valveHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("internalCoatThickness.in=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByInternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where internalCoatThickness is not null
        defaultValveHistShouldBeFound("internalCoatThickness.specified=true");

        // Get all the valveHistList where internalCoatThickness is null
        defaultValveHistShouldNotBeFound("internalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultValveHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the valveHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultValveHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the valveHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where externalCoatThickness is not null
        defaultValveHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the valveHistList where externalCoatThickness is null
        defaultValveHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsConcrCoatingIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isConcrCoating equals to DEFAULT_IS_CONCR_COATING
        defaultValveHistShouldBeFound("isConcrCoating.equals=" + DEFAULT_IS_CONCR_COATING);

        // Get all the valveHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultValveHistShouldNotBeFound("isConcrCoating.equals=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsConcrCoatingIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isConcrCoating in DEFAULT_IS_CONCR_COATING or UPDATED_IS_CONCR_COATING
        defaultValveHistShouldBeFound("isConcrCoating.in=" + DEFAULT_IS_CONCR_COATING + "," + UPDATED_IS_CONCR_COATING);

        // Get all the valveHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultValveHistShouldNotBeFound("isConcrCoating.in=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsConcrCoatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isConcrCoating is not null
        defaultValveHistShouldBeFound("isConcrCoating.specified=true");

        // Get all the valveHistList where isConcrCoating is null
        defaultValveHistShouldNotBeFound("isConcrCoating.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsConcrCoatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isConcrCoating greater than or equals to DEFAULT_IS_CONCR_COATING
        defaultValveHistShouldBeFound("isConcrCoating.greaterOrEqualThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the valveHistList where isConcrCoating greater than or equals to UPDATED_IS_CONCR_COATING
        defaultValveHistShouldNotBeFound("isConcrCoating.greaterOrEqualThan=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsConcrCoatingIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isConcrCoating less than or equals to DEFAULT_IS_CONCR_COATING
        defaultValveHistShouldNotBeFound("isConcrCoating.lessThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the valveHistList where isConcrCoating less than or equals to UPDATED_IS_CONCR_COATING
        defaultValveHistShouldBeFound("isConcrCoating.lessThan=" + UPDATED_IS_CONCR_COATING);
    }


    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatThickness equals to DEFAULT_CONCR_COAT_THICKNESS
        defaultValveHistShouldBeFound("concrCoatThickness.equals=" + DEFAULT_CONCR_COAT_THICKNESS);

        // Get all the valveHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("concrCoatThickness.equals=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatThickness in DEFAULT_CONCR_COAT_THICKNESS or UPDATED_CONCR_COAT_THICKNESS
        defaultValveHistShouldBeFound("concrCoatThickness.in=" + DEFAULT_CONCR_COAT_THICKNESS + "," + UPDATED_CONCR_COAT_THICKNESS);

        // Get all the valveHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultValveHistShouldNotBeFound("concrCoatThickness.in=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatThickness is not null
        defaultValveHistShouldBeFound("concrCoatThickness.specified=true");

        // Get all the valveHistList where concrCoatThickness is null
        defaultValveHistShouldNotBeFound("concrCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatDensity equals to DEFAULT_CONCR_COAT_DENSITY
        defaultValveHistShouldBeFound("concrCoatDensity.equals=" + DEFAULT_CONCR_COAT_DENSITY);

        // Get all the valveHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultValveHistShouldNotBeFound("concrCoatDensity.equals=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatDensityIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatDensity in DEFAULT_CONCR_COAT_DENSITY or UPDATED_CONCR_COAT_DENSITY
        defaultValveHistShouldBeFound("concrCoatDensity.in=" + DEFAULT_CONCR_COAT_DENSITY + "," + UPDATED_CONCR_COAT_DENSITY);

        // Get all the valveHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultValveHistShouldNotBeFound("concrCoatDensity.in=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllValveHistsByConcrCoatDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where concrCoatDensity is not null
        defaultValveHistShouldBeFound("concrCoatDensity.specified=true");

        // Get all the valveHistList where concrCoatDensity is null
        defaultValveHistShouldNotBeFound("concrCoatDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByMeasWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where measWallThickness equals to DEFAULT_MEAS_WALL_THICKNESS
        defaultValveHistShouldBeFound("measWallThickness.equals=" + DEFAULT_MEAS_WALL_THICKNESS);

        // Get all the valveHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultValveHistShouldNotBeFound("measWallThickness.equals=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMeasWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where measWallThickness in DEFAULT_MEAS_WALL_THICKNESS or UPDATED_MEAS_WALL_THICKNESS
        defaultValveHistShouldBeFound("measWallThickness.in=" + DEFAULT_MEAS_WALL_THICKNESS + "," + UPDATED_MEAS_WALL_THICKNESS);

        // Get all the valveHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultValveHistShouldNotBeFound("measWallThickness.in=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMeasWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where measWallThickness is not null
        defaultValveHistShouldBeFound("measWallThickness.specified=true");

        // Get all the valveHistList where measWallThickness is null
        defaultValveHistShouldNotBeFound("measWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where length equals to DEFAULT_LENGTH
        defaultValveHistShouldBeFound("length.equals=" + DEFAULT_LENGTH);

        // Get all the valveHistList where length equals to UPDATED_LENGTH
        defaultValveHistShouldNotBeFound("length.equals=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllValveHistsByLengthIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where length in DEFAULT_LENGTH or UPDATED_LENGTH
        defaultValveHistShouldBeFound("length.in=" + DEFAULT_LENGTH + "," + UPDATED_LENGTH);

        // Get all the valveHistList where length equals to UPDATED_LENGTH
        defaultValveHistShouldNotBeFound("length.in=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllValveHistsByLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where length is not null
        defaultValveHistShouldBeFound("length.specified=true");

        // Get all the valveHistList where length is null
        defaultValveHistShouldNotBeFound("length.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where length greater than or equals to DEFAULT_LENGTH
        defaultValveHistShouldBeFound("length.greaterOrEqualThan=" + DEFAULT_LENGTH);

        // Get all the valveHistList where length greater than or equals to UPDATED_LENGTH
        defaultValveHistShouldNotBeFound("length.greaterOrEqualThan=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllValveHistsByLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where length less than or equals to DEFAULT_LENGTH
        defaultValveHistShouldNotBeFound("length.lessThan=" + DEFAULT_LENGTH);

        // Get all the valveHistList where length less than or equals to UPDATED_LENGTH
        defaultValveHistShouldBeFound("length.lessThan=" + UPDATED_LENGTH);
    }


    @Test
    @Transactional
    public void getAllValveHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where weight equals to DEFAULT_WEIGHT
        defaultValveHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the valveHistList where weight equals to UPDATED_WEIGHT
        defaultValveHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultValveHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the valveHistList where weight equals to UPDATED_WEIGHT
        defaultValveHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where weight is not null
        defaultValveHistShouldBeFound("weight.specified=true");

        // Get all the valveHistList where weight is null
        defaultValveHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmtsIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smts equals to DEFAULT_SMTS
        defaultValveHistShouldBeFound("smts.equals=" + DEFAULT_SMTS);

        // Get all the valveHistList where smts equals to UPDATED_SMTS
        defaultValveHistShouldNotBeFound("smts.equals=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmtsIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smts in DEFAULT_SMTS or UPDATED_SMTS
        defaultValveHistShouldBeFound("smts.in=" + DEFAULT_SMTS + "," + UPDATED_SMTS);

        // Get all the valveHistList where smts equals to UPDATED_SMTS
        defaultValveHistShouldNotBeFound("smts.in=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmtsIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smts is not null
        defaultValveHistShouldBeFound("smts.specified=true");

        // Get all the valveHistList where smts is null
        defaultValveHistShouldNotBeFound("smts.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmysIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smys equals to DEFAULT_SMYS
        defaultValveHistShouldBeFound("smys.equals=" + DEFAULT_SMYS);

        // Get all the valveHistList where smys equals to UPDATED_SMYS
        defaultValveHistShouldNotBeFound("smys.equals=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmysIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smys in DEFAULT_SMYS or UPDATED_SMYS
        defaultValveHistShouldBeFound("smys.in=" + DEFAULT_SMYS + "," + UPDATED_SMYS);

        // Get all the valveHistList where smys equals to UPDATED_SMYS
        defaultValveHistShouldNotBeFound("smys.in=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySmysIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where smys is not null
        defaultValveHistShouldBeFound("smys.specified=true");

        // Get all the valveHistList where smys is null
        defaultValveHistShouldNotBeFound("smys.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdbmIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdbm equals to DEFAULT_SDBM
        defaultValveHistShouldBeFound("sdbm.equals=" + DEFAULT_SDBM);

        // Get all the valveHistList where sdbm equals to UPDATED_SDBM
        defaultValveHistShouldNotBeFound("sdbm.equals=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdbmIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdbm in DEFAULT_SDBM or UPDATED_SDBM
        defaultValveHistShouldBeFound("sdbm.in=" + DEFAULT_SDBM + "," + UPDATED_SDBM);

        // Get all the valveHistList where sdbm equals to UPDATED_SDBM
        defaultValveHistShouldNotBeFound("sdbm.in=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdbmIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdbm is not null
        defaultValveHistShouldBeFound("sdbm.specified=true");

        // Get all the valveHistList where sdbm is null
        defaultValveHistShouldNotBeFound("sdbm.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdafIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdaf equals to DEFAULT_SDAF
        defaultValveHistShouldBeFound("sdaf.equals=" + DEFAULT_SDAF);

        // Get all the valveHistList where sdaf equals to UPDATED_SDAF
        defaultValveHistShouldNotBeFound("sdaf.equals=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdafIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdaf in DEFAULT_SDAF or UPDATED_SDAF
        defaultValveHistShouldBeFound("sdaf.in=" + DEFAULT_SDAF + "," + UPDATED_SDAF);

        // Get all the valveHistList where sdaf equals to UPDATED_SDAF
        defaultValveHistShouldNotBeFound("sdaf.in=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdafIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdaf is not null
        defaultValveHistShouldBeFound("sdaf.specified=true");

        // Get all the valveHistList where sdaf is null
        defaultValveHistShouldNotBeFound("sdaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdcsIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdcs equals to DEFAULT_SDCS
        defaultValveHistShouldBeFound("sdcs.equals=" + DEFAULT_SDCS);

        // Get all the valveHistList where sdcs equals to UPDATED_SDCS
        defaultValveHistShouldNotBeFound("sdcs.equals=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdcsIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdcs in DEFAULT_SDCS or UPDATED_SDCS
        defaultValveHistShouldBeFound("sdcs.in=" + DEFAULT_SDCS + "," + UPDATED_SDCS);

        // Get all the valveHistList where sdcs equals to UPDATED_SDCS
        defaultValveHistShouldNotBeFound("sdcs.in=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySdcsIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where sdcs is not null
        defaultValveHistShouldBeFound("sdcs.specified=true");

        // Get all the valveHistList where sdcs is null
        defaultValveHistShouldNotBeFound("sdcs.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByAllowTensStrainIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where allowTensStrain equals to DEFAULT_ALLOW_TENS_STRAIN
        defaultValveHistShouldBeFound("allowTensStrain.equals=" + DEFAULT_ALLOW_TENS_STRAIN);

        // Get all the valveHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultValveHistShouldNotBeFound("allowTensStrain.equals=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllValveHistsByAllowTensStrainIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where allowTensStrain in DEFAULT_ALLOW_TENS_STRAIN or UPDATED_ALLOW_TENS_STRAIN
        defaultValveHistShouldBeFound("allowTensStrain.in=" + DEFAULT_ALLOW_TENS_STRAIN + "," + UPDATED_ALLOW_TENS_STRAIN);

        // Get all the valveHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultValveHistShouldNotBeFound("allowTensStrain.in=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllValveHistsByAllowTensStrainIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where allowTensStrain is not null
        defaultValveHistShouldBeFound("allowTensStrain.specified=true");

        // Get all the valveHistList where allowTensStrain is null
        defaultValveHistShouldNotBeFound("allowTensStrain.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByCorrosionAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where corrosionAllowance equals to DEFAULT_CORROSION_ALLOWANCE
        defaultValveHistShouldBeFound("corrosionAllowance.equals=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the valveHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultValveHistShouldNotBeFound("corrosionAllowance.equals=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCorrosionAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where corrosionAllowance in DEFAULT_CORROSION_ALLOWANCE or UPDATED_CORROSION_ALLOWANCE
        defaultValveHistShouldBeFound("corrosionAllowance.in=" + DEFAULT_CORROSION_ALLOWANCE + "," + UPDATED_CORROSION_ALLOWANCE);

        // Get all the valveHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultValveHistShouldNotBeFound("corrosionAllowance.in=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCorrosionAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where corrosionAllowance is not null
        defaultValveHistShouldBeFound("corrosionAllowance.specified=true");

        // Get all the valveHistList where corrosionAllowance is null
        defaultValveHistShouldNotBeFound("corrosionAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByCorrosionAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where corrosionAllowance greater than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultValveHistShouldBeFound("corrosionAllowance.greaterOrEqualThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the valveHistList where corrosionAllowance greater than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultValveHistShouldNotBeFound("corrosionAllowance.greaterOrEqualThan=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCorrosionAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where corrosionAllowance less than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultValveHistShouldNotBeFound("corrosionAllowance.lessThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the valveHistList where corrosionAllowance less than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultValveHistShouldBeFound("corrosionAllowance.lessThan=" + UPDATED_CORROSION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllValveHistsByFabricationAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where fabricationAllowance equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultValveHistShouldBeFound("fabricationAllowance.equals=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the valveHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultValveHistShouldNotBeFound("fabricationAllowance.equals=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByFabricationAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where fabricationAllowance in DEFAULT_FABRICATION_ALLOWANCE or UPDATED_FABRICATION_ALLOWANCE
        defaultValveHistShouldBeFound("fabricationAllowance.in=" + DEFAULT_FABRICATION_ALLOWANCE + "," + UPDATED_FABRICATION_ALLOWANCE);

        // Get all the valveHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultValveHistShouldNotBeFound("fabricationAllowance.in=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByFabricationAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where fabricationAllowance is not null
        defaultValveHistShouldBeFound("fabricationAllowance.specified=true");

        // Get all the valveHistList where fabricationAllowance is null
        defaultValveHistShouldNotBeFound("fabricationAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByFabricationAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where fabricationAllowance greater than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultValveHistShouldBeFound("fabricationAllowance.greaterOrEqualThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the valveHistList where fabricationAllowance greater than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultValveHistShouldNotBeFound("fabricationAllowance.greaterOrEqualThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByFabricationAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where fabricationAllowance less than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultValveHistShouldNotBeFound("fabricationAllowance.lessThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the valveHistList where fabricationAllowance less than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultValveHistShouldBeFound("fabricationAllowance.lessThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllValveHistsByDateManufacturedIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateManufactured equals to DEFAULT_DATE_MANUFACTURED
        defaultValveHistShouldBeFound("dateManufactured.equals=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the valveHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultValveHistShouldNotBeFound("dateManufactured.equals=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateManufacturedIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateManufactured in DEFAULT_DATE_MANUFACTURED or UPDATED_DATE_MANUFACTURED
        defaultValveHistShouldBeFound("dateManufactured.in=" + DEFAULT_DATE_MANUFACTURED + "," + UPDATED_DATE_MANUFACTURED);

        // Get all the valveHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultValveHistShouldNotBeFound("dateManufactured.in=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateManufacturedIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateManufactured is not null
        defaultValveHistShouldBeFound("dateManufactured.specified=true");

        // Get all the valveHistList where dateManufactured is null
        defaultValveHistShouldNotBeFound("dateManufactured.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateManufacturedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateManufactured greater than or equals to DEFAULT_DATE_MANUFACTURED
        defaultValveHistShouldBeFound("dateManufactured.greaterOrEqualThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the valveHistList where dateManufactured greater than or equals to UPDATED_DATE_MANUFACTURED
        defaultValveHistShouldNotBeFound("dateManufactured.greaterOrEqualThan=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateManufacturedIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateManufactured less than or equals to DEFAULT_DATE_MANUFACTURED
        defaultValveHistShouldNotBeFound("dateManufactured.lessThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the valveHistList where dateManufactured less than or equals to UPDATED_DATE_MANUFACTURED
        defaultValveHistShouldBeFound("dateManufactured.lessThan=" + UPDATED_DATE_MANUFACTURED);
    }


    @Test
    @Transactional
    public void getAllValveHistsByMillTestPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where millTestPressure equals to DEFAULT_MILL_TEST_PRESSURE
        defaultValveHistShouldBeFound("millTestPressure.equals=" + DEFAULT_MILL_TEST_PRESSURE);

        // Get all the valveHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultValveHistShouldNotBeFound("millTestPressure.equals=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMillTestPressureIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where millTestPressure in DEFAULT_MILL_TEST_PRESSURE or UPDATED_MILL_TEST_PRESSURE
        defaultValveHistShouldBeFound("millTestPressure.in=" + DEFAULT_MILL_TEST_PRESSURE + "," + UPDATED_MILL_TEST_PRESSURE);

        // Get all the valveHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultValveHistShouldNotBeFound("millTestPressure.in=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMillTestPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where millTestPressure is not null
        defaultValveHistShouldBeFound("millTestPressure.specified=true");

        // Get all the valveHistList where millTestPressure is null
        defaultValveHistShouldNotBeFound("millTestPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressure equals to DEFAULT_DESIGN_PRESSURE
        defaultValveHistShouldBeFound("designPressure.equals=" + DEFAULT_DESIGN_PRESSURE);

        // Get all the valveHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultValveHistShouldNotBeFound("designPressure.equals=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressure in DEFAULT_DESIGN_PRESSURE or UPDATED_DESIGN_PRESSURE
        defaultValveHistShouldBeFound("designPressure.in=" + DEFAULT_DESIGN_PRESSURE + "," + UPDATED_DESIGN_PRESSURE);

        // Get all the valveHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultValveHistShouldNotBeFound("designPressure.in=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressure is not null
        defaultValveHistShouldBeFound("designPressure.specified=true");

        // Get all the valveHistList where designPressure is null
        defaultValveHistShouldNotBeFound("designPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureInIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureIn equals to DEFAULT_DESIGN_PRESSURE_IN
        defaultValveHistShouldBeFound("designPressureIn.equals=" + DEFAULT_DESIGN_PRESSURE_IN);

        // Get all the valveHistList where designPressureIn equals to UPDATED_DESIGN_PRESSURE_IN
        defaultValveHistShouldNotBeFound("designPressureIn.equals=" + UPDATED_DESIGN_PRESSURE_IN);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureInIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureIn in DEFAULT_DESIGN_PRESSURE_IN or UPDATED_DESIGN_PRESSURE_IN
        defaultValveHistShouldBeFound("designPressureIn.in=" + DEFAULT_DESIGN_PRESSURE_IN + "," + UPDATED_DESIGN_PRESSURE_IN);

        // Get all the valveHistList where designPressureIn equals to UPDATED_DESIGN_PRESSURE_IN
        defaultValveHistShouldNotBeFound("designPressureIn.in=" + UPDATED_DESIGN_PRESSURE_IN);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureInIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureIn is not null
        defaultValveHistShouldBeFound("designPressureIn.specified=true");

        // Get all the valveHistList where designPressureIn is null
        defaultValveHistShouldNotBeFound("designPressureIn.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureOutIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureOut equals to DEFAULT_DESIGN_PRESSURE_OUT
        defaultValveHistShouldBeFound("designPressureOut.equals=" + DEFAULT_DESIGN_PRESSURE_OUT);

        // Get all the valveHistList where designPressureOut equals to UPDATED_DESIGN_PRESSURE_OUT
        defaultValveHistShouldNotBeFound("designPressureOut.equals=" + UPDATED_DESIGN_PRESSURE_OUT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureOutIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureOut in DEFAULT_DESIGN_PRESSURE_OUT or UPDATED_DESIGN_PRESSURE_OUT
        defaultValveHistShouldBeFound("designPressureOut.in=" + DEFAULT_DESIGN_PRESSURE_OUT + "," + UPDATED_DESIGN_PRESSURE_OUT);

        // Get all the valveHistList where designPressureOut equals to UPDATED_DESIGN_PRESSURE_OUT
        defaultValveHistShouldNotBeFound("designPressureOut.in=" + UPDATED_DESIGN_PRESSURE_OUT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignPressureOutIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designPressureOut is not null
        defaultValveHistShouldBeFound("designPressureOut.specified=true");

        // Get all the valveHistList where designPressureOut is null
        defaultValveHistShouldNotBeFound("designPressureOut.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIncidentalPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where incidentalPressure equals to DEFAULT_INCIDENTAL_PRESSURE
        defaultValveHistShouldBeFound("incidentalPressure.equals=" + DEFAULT_INCIDENTAL_PRESSURE);

        // Get all the valveHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultValveHistShouldNotBeFound("incidentalPressure.equals=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIncidentalPressureIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where incidentalPressure in DEFAULT_INCIDENTAL_PRESSURE or UPDATED_INCIDENTAL_PRESSURE
        defaultValveHistShouldBeFound("incidentalPressure.in=" + DEFAULT_INCIDENTAL_PRESSURE + "," + UPDATED_INCIDENTAL_PRESSURE);

        // Get all the valveHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultValveHistShouldNotBeFound("incidentalPressure.in=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIncidentalPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where incidentalPressure is not null
        defaultValveHistShouldBeFound("incidentalPressure.specified=true");

        // Get all the valveHistList where incidentalPressure is null
        defaultValveHistShouldNotBeFound("incidentalPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateInstalledIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateInstalled equals to DEFAULT_DATE_INSTALLED
        defaultValveHistShouldBeFound("dateInstalled.equals=" + DEFAULT_DATE_INSTALLED);

        // Get all the valveHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultValveHistShouldNotBeFound("dateInstalled.equals=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateInstalledIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateInstalled in DEFAULT_DATE_INSTALLED or UPDATED_DATE_INSTALLED
        defaultValveHistShouldBeFound("dateInstalled.in=" + DEFAULT_DATE_INSTALLED + "," + UPDATED_DATE_INSTALLED);

        // Get all the valveHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultValveHistShouldNotBeFound("dateInstalled.in=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateInstalledIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateInstalled is not null
        defaultValveHistShouldBeFound("dateInstalled.specified=true");

        // Get all the valveHistList where dateInstalled is null
        defaultValveHistShouldNotBeFound("dateInstalled.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateInstalledIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateInstalled greater than or equals to DEFAULT_DATE_INSTALLED
        defaultValveHistShouldBeFound("dateInstalled.greaterOrEqualThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the valveHistList where dateInstalled greater than or equals to UPDATED_DATE_INSTALLED
        defaultValveHistShouldNotBeFound("dateInstalled.greaterOrEqualThan=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateInstalledIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateInstalled less than or equals to DEFAULT_DATE_INSTALLED
        defaultValveHistShouldNotBeFound("dateInstalled.lessThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the valveHistList where dateInstalled less than or equals to UPDATED_DATE_INSTALLED
        defaultValveHistShouldBeFound("dateInstalled.lessThan=" + UPDATED_DATE_INSTALLED);
    }


    @Test
    @Transactional
    public void getAllValveHistsBySeamOrientIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamOrient equals to DEFAULT_SEAM_ORIENT
        defaultValveHistShouldBeFound("seamOrient.equals=" + DEFAULT_SEAM_ORIENT);

        // Get all the valveHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultValveHistShouldNotBeFound("seamOrient.equals=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeamOrientIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamOrient in DEFAULT_SEAM_ORIENT or UPDATED_SEAM_ORIENT
        defaultValveHistShouldBeFound("seamOrient.in=" + DEFAULT_SEAM_ORIENT + "," + UPDATED_SEAM_ORIENT);

        // Get all the valveHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultValveHistShouldNotBeFound("seamOrient.in=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeamOrientIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamOrient is not null
        defaultValveHistShouldBeFound("seamOrient.specified=true");

        // Get all the valveHistList where seamOrient is null
        defaultValveHistShouldNotBeFound("seamOrient.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeamAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamAngle equals to DEFAULT_SEAM_ANGLE
        defaultValveHistShouldBeFound("seamAngle.equals=" + DEFAULT_SEAM_ANGLE);

        // Get all the valveHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultValveHistShouldNotBeFound("seamAngle.equals=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeamAngleIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamAngle in DEFAULT_SEAM_ANGLE or UPDATED_SEAM_ANGLE
        defaultValveHistShouldBeFound("seamAngle.in=" + DEFAULT_SEAM_ANGLE + "," + UPDATED_SEAM_ANGLE);

        // Get all the valveHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultValveHistShouldNotBeFound("seamAngle.in=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeamAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seamAngle is not null
        defaultValveHistShouldBeFound("seamAngle.specified=true");

        // Get all the valveHistList where seamAngle is null
        defaultValveHistShouldNotBeFound("seamAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByAzimuthIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where azimuth equals to DEFAULT_AZIMUTH
        defaultValveHistShouldBeFound("azimuth.equals=" + DEFAULT_AZIMUTH);

        // Get all the valveHistList where azimuth equals to UPDATED_AZIMUTH
        defaultValveHistShouldNotBeFound("azimuth.equals=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllValveHistsByAzimuthIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where azimuth in DEFAULT_AZIMUTH or UPDATED_AZIMUTH
        defaultValveHistShouldBeFound("azimuth.in=" + DEFAULT_AZIMUTH + "," + UPDATED_AZIMUTH);

        // Get all the valveHistList where azimuth equals to UPDATED_AZIMUTH
        defaultValveHistShouldNotBeFound("azimuth.in=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllValveHistsByAzimuthIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where azimuth is not null
        defaultValveHistShouldBeFound("azimuth.specified=true");

        // Get all the valveHistList where azimuth is null
        defaultValveHistShouldNotBeFound("azimuth.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeabInstallTempIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seabInstallTemp equals to DEFAULT_SEAB_INSTALL_TEMP
        defaultValveHistShouldBeFound("seabInstallTemp.equals=" + DEFAULT_SEAB_INSTALL_TEMP);

        // Get all the valveHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultValveHistShouldNotBeFound("seabInstallTemp.equals=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeabInstallTempIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seabInstallTemp in DEFAULT_SEAB_INSTALL_TEMP or UPDATED_SEAB_INSTALL_TEMP
        defaultValveHistShouldBeFound("seabInstallTemp.in=" + DEFAULT_SEAB_INSTALL_TEMP + "," + UPDATED_SEAB_INSTALL_TEMP);

        // Get all the valveHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultValveHistShouldNotBeFound("seabInstallTemp.in=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllValveHistsBySeabInstallTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where seabInstallTemp is not null
        defaultValveHistShouldBeFound("seabInstallTemp.specified=true");

        // Get all the valveHistList where seabInstallTemp is null
        defaultValveHistShouldNotBeFound("seabInstallTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByVerticalAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where verticalAngle equals to DEFAULT_VERTICAL_ANGLE
        defaultValveHistShouldBeFound("verticalAngle.equals=" + DEFAULT_VERTICAL_ANGLE);

        // Get all the valveHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultValveHistShouldNotBeFound("verticalAngle.equals=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByVerticalAngleIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where verticalAngle in DEFAULT_VERTICAL_ANGLE or UPDATED_VERTICAL_ANGLE
        defaultValveHistShouldBeFound("verticalAngle.in=" + DEFAULT_VERTICAL_ANGLE + "," + UPDATED_VERTICAL_ANGLE);

        // Get all the valveHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultValveHistShouldNotBeFound("verticalAngle.in=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByVerticalAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where verticalAngle is not null
        defaultValveHistShouldBeFound("verticalAngle.specified=true");

        // Get all the valveHistList where verticalAngle is null
        defaultValveHistShouldNotBeFound("verticalAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatTreatDuratIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatTreatDurat equals to DEFAULT_HEAT_TREAT_DURAT
        defaultValveHistShouldBeFound("heatTreatDurat.equals=" + DEFAULT_HEAT_TREAT_DURAT);

        // Get all the valveHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultValveHistShouldNotBeFound("heatTreatDurat.equals=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatTreatDuratIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatTreatDurat in DEFAULT_HEAT_TREAT_DURAT or UPDATED_HEAT_TREAT_DURAT
        defaultValveHistShouldBeFound("heatTreatDurat.in=" + DEFAULT_HEAT_TREAT_DURAT + "," + UPDATED_HEAT_TREAT_DURAT);

        // Get all the valveHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultValveHistShouldNotBeFound("heatTreatDurat.in=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatTreatDuratIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatTreatDurat is not null
        defaultValveHistShouldBeFound("heatTreatDurat.specified=true");

        // Get all the valveHistList where heatTreatDurat is null
        defaultValveHistShouldNotBeFound("heatTreatDurat.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByMaxDesignTempIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where maxDesignTemp equals to DEFAULT_MAX_DESIGN_TEMP
        defaultValveHistShouldBeFound("maxDesignTemp.equals=" + DEFAULT_MAX_DESIGN_TEMP);

        // Get all the valveHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultValveHistShouldNotBeFound("maxDesignTemp.equals=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMaxDesignTempIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where maxDesignTemp in DEFAULT_MAX_DESIGN_TEMP or UPDATED_MAX_DESIGN_TEMP
        defaultValveHistShouldBeFound("maxDesignTemp.in=" + DEFAULT_MAX_DESIGN_TEMP + "," + UPDATED_MAX_DESIGN_TEMP);

        // Get all the valveHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultValveHistShouldNotBeFound("maxDesignTemp.in=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllValveHistsByMaxDesignTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where maxDesignTemp is not null
        defaultValveHistShouldBeFound("maxDesignTemp.specified=true");

        // Get all the valveHistList where maxDesignTemp is null
        defaultValveHistShouldNotBeFound("maxDesignTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatNumber equals to DEFAULT_HEAT_NUMBER
        defaultValveHistShouldBeFound("heatNumber.equals=" + DEFAULT_HEAT_NUMBER);

        // Get all the valveHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultValveHistShouldNotBeFound("heatNumber.equals=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatNumberIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatNumber in DEFAULT_HEAT_NUMBER or UPDATED_HEAT_NUMBER
        defaultValveHistShouldBeFound("heatNumber.in=" + DEFAULT_HEAT_NUMBER + "," + UPDATED_HEAT_NUMBER);

        // Get all the valveHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultValveHistShouldNotBeFound("heatNumber.in=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllValveHistsByHeatNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where heatNumber is not null
        defaultValveHistShouldBeFound("heatNumber.specified=true");

        // Get all the valveHistList where heatNumber is null
        defaultValveHistShouldNotBeFound("heatNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where coord equals to DEFAULT_COORD
        defaultValveHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the valveHistList where coord equals to UPDATED_COORD
        defaultValveHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultValveHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the valveHistList where coord equals to UPDATED_COORD
        defaultValveHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where coord is not null
        defaultValveHistShouldBeFound("coord.specified=true");

        // Get all the valveHistList where coord is null
        defaultValveHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designCoord equals to DEFAULT_DESIGN_COORD
        defaultValveHistShouldBeFound("designCoord.equals=" + DEFAULT_DESIGN_COORD);

        // Get all the valveHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultValveHistShouldNotBeFound("designCoord.equals=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignCoordIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designCoord in DEFAULT_DESIGN_COORD or UPDATED_DESIGN_COORD
        defaultValveHistShouldBeFound("designCoord.in=" + DEFAULT_DESIGN_COORD + "," + UPDATED_DESIGN_COORD);

        // Get all the valveHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultValveHistShouldNotBeFound("designCoord.in=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDesignCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where designCoord is not null
        defaultValveHistShouldBeFound("designCoord.specified=true");

        // Get all the valveHistList where designCoord is null
        defaultValveHistShouldNotBeFound("designCoord.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByKpInstIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where kpInst equals to DEFAULT_KP_INST
        defaultValveHistShouldBeFound("kpInst.equals=" + DEFAULT_KP_INST);

        // Get all the valveHistList where kpInst equals to UPDATED_KP_INST
        defaultValveHistShouldNotBeFound("kpInst.equals=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllValveHistsByKpInstIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where kpInst in DEFAULT_KP_INST or UPDATED_KP_INST
        defaultValveHistShouldBeFound("kpInst.in=" + DEFAULT_KP_INST + "," + UPDATED_KP_INST);

        // Get all the valveHistList where kpInst equals to UPDATED_KP_INST
        defaultValveHistShouldNotBeFound("kpInst.in=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllValveHistsByKpInstIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where kpInst is not null
        defaultValveHistShouldBeFound("kpInst.specified=true");

        // Get all the valveHistList where kpInst is null
        defaultValveHistShouldNotBeFound("kpInst.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultValveHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the valveHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultValveHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultValveHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the valveHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultValveHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isCurrentFlag is not null
        defaultValveHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the valveHistList where isCurrentFlag is null
        defaultValveHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultValveHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the valveHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultValveHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllValveHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultValveHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the valveHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultValveHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllValveHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where description equals to DEFAULT_DESCRIPTION
        defaultValveHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the valveHistList where description equals to UPDATED_DESCRIPTION
        defaultValveHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultValveHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the valveHistList where description equals to UPDATED_DESCRIPTION
        defaultValveHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where description is not null
        defaultValveHistShouldBeFound("description.specified=true");

        // Get all the valveHistList where description is null
        defaultValveHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where comment equals to DEFAULT_COMMENT
        defaultValveHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the valveHistList where comment equals to UPDATED_COMMENT
        defaultValveHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultValveHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the valveHistList where comment equals to UPDATED_COMMENT
        defaultValveHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where comment is not null
        defaultValveHistShouldBeFound("comment.specified=true");

        // Get all the valveHistList where comment is null
        defaultValveHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultValveHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the valveHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultValveHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultValveHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the valveHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultValveHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateCreate is not null
        defaultValveHistShouldBeFound("dateCreate.specified=true");

        // Get all the valveHistList where dateCreate is null
        defaultValveHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultValveHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the valveHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultValveHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultValveHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the valveHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultValveHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllValveHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where dateEdit is not null
        defaultValveHistShouldBeFound("dateEdit.specified=true");

        // Get all the valveHistList where dateEdit is null
        defaultValveHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where creator equals to DEFAULT_CREATOR
        defaultValveHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the valveHistList where creator equals to UPDATED_CREATOR
        defaultValveHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultValveHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the valveHistList where creator equals to UPDATED_CREATOR
        defaultValveHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllValveHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where creator is not null
        defaultValveHistShouldBeFound("creator.specified=true");

        // Get all the valveHistList where creator is null
        defaultValveHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where editor equals to DEFAULT_EDITOR
        defaultValveHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the valveHistList where editor equals to UPDATED_EDITOR
        defaultValveHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllValveHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultValveHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the valveHistList where editor equals to UPDATED_EDITOR
        defaultValveHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllValveHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        // Get all the valveHistList where editor is not null
        defaultValveHistShouldBeFound("editor.specified=true");

        // Get all the valveHistList where editor is null
        defaultValveHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllValveHistsByValveIsEqualToSomething() throws Exception {
        // Initialize the database
        Valve valve = ValveResourceIT.createEntity(em);
        em.persist(valve);
        em.flush();
        valveHist.setValve(valve);
        valveHistRepository.saveAndFlush(valveHist);
        Long valveId = valve.getId();

        // Get all the valveHistList where valve equals to valveId
        defaultValveHistShouldBeFound("valveId.equals=" + valveId);

        // Get all the valveHistList where valve equals to valveId + 1
        defaultValveHistShouldNotBeFound("valveId.equals=" + (valveId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = valveHist.getIdPipelineSection();
        valveHistRepository.saveAndFlush(valveHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the valveHistList where idPipelineSection equals to idPipelineSectionId
        defaultValveHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the valveHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultValveHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListValveType idType = ListValveTypeResourceIT.createEntity(em);
        em.persist(idType);
        em.flush();
        valveHist.setIdType(idType);
        valveHistRepository.saveAndFlush(valveHist);
        Long idTypeId = idType.getId();

        // Get all the valveHistList where idType equals to idTypeId
        defaultValveHistShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the valveHistList where idType equals to idTypeId + 1
        defaultValveHistShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdInternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListInternalCoating idInternalCoatType = ListInternalCoatingResourceIT.createEntity(em);
        em.persist(idInternalCoatType);
        em.flush();
        valveHist.setIdInternalCoatType(idInternalCoatType);
        valveHistRepository.saveAndFlush(valveHist);
        Long idInternalCoatTypeId = idInternalCoatType.getId();

        // Get all the valveHistList where idInternalCoatType equals to idInternalCoatTypeId
        defaultValveHistShouldBeFound("idInternalCoatTypeId.equals=" + idInternalCoatTypeId);

        // Get all the valveHistList where idInternalCoatType equals to idInternalCoatTypeId + 1
        defaultValveHistShouldNotBeFound("idInternalCoatTypeId.equals=" + (idInternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListExternalCoating idExternalCoatType = ListExternalCoatingResourceIT.createEntity(em);
        em.persist(idExternalCoatType);
        em.flush();
        valveHist.setIdExternalCoatType(idExternalCoatType);
        valveHistRepository.saveAndFlush(valveHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the valveHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultValveHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the valveHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultValveHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdNominalWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        ListNominalWallThickness idNominalWallThickness = ListNominalWallThicknessResourceIT.createEntity(em);
        em.persist(idNominalWallThickness);
        em.flush();
        valveHist.setIdNominalWallThickness(idNominalWallThickness);
        valveHistRepository.saveAndFlush(valveHist);
        Long idNominalWallThicknessId = idNominalWallThickness.getId();

        // Get all the valveHistList where idNominalWallThickness equals to idNominalWallThicknessId
        defaultValveHistShouldBeFound("idNominalWallThicknessId.equals=" + idNominalWallThicknessId);

        // Get all the valveHistList where idNominalWallThickness equals to idNominalWallThicknessId + 1
        defaultValveHistShouldNotBeFound("idNominalWallThicknessId.equals=" + (idNominalWallThicknessId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdPipeJointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint idPipeJoint = PipejointResourceIT.createEntity(em);
        em.persist(idPipeJoint);
        em.flush();
        valveHist.setIdPipeJoint(idPipeJoint);
        valveHistRepository.saveAndFlush(valveHist);
        Long idPipeJointId = idPipeJoint.getId();

        // Get all the valveHistList where idPipeJoint equals to idPipeJointId
        defaultValveHistShouldBeFound("idPipeJointId.equals=" + idPipeJointId);

        // Get all the valveHistList where idPipeJoint equals to idPipeJointId + 1
        defaultValveHistShouldNotBeFound("idPipeJointId.equals=" + (idPipeJointId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdManufacturerIsEqualToSomething() throws Exception {
        // Initialize the database
        ListValveManufacturer idManufacturer = ListValveManufacturerResourceIT.createEntity(em);
        em.persist(idManufacturer);
        em.flush();
        valveHist.setIdManufacturer(idManufacturer);
        valveHistRepository.saveAndFlush(valveHist);
        Long idManufacturerId = idManufacturer.getId();

        // Get all the valveHistList where idManufacturer equals to idManufacturerId
        defaultValveHistShouldBeFound("idManufacturerId.equals=" + idManufacturerId);

        // Get all the valveHistList where idManufacturer equals to idManufacturerId + 1
        defaultValveHistShouldNotBeFound("idManufacturerId.equals=" + (idManufacturerId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListValveSpecification idSpecification = ListValveSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        valveHist.setIdSpecification(idSpecification);
        valveHistRepository.saveAndFlush(valveHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the valveHistList where idSpecification equals to idSpecificationId
        defaultValveHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the valveHistList where idSpecification equals to idSpecificationId + 1
        defaultValveHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdFunctionIsEqualToSomething() throws Exception {
        // Initialize the database
        ListValveFunction idFunction = ListValveFunctionResourceIT.createEntity(em);
        em.persist(idFunction);
        em.flush();
        valveHist.setIdFunction(idFunction);
        valveHistRepository.saveAndFlush(valveHist);
        Long idFunctionId = idFunction.getId();

        // Get all the valveHistList where idFunction equals to idFunctionId
        defaultValveHistShouldBeFound("idFunctionId.equals=" + idFunctionId);

        // Get all the valveHistList where idFunction equals to idFunctionId + 1
        defaultValveHistShouldNotBeFound("idFunctionId.equals=" + (idFunctionId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdLongSeamWeldTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListLongSeamWeldType idLongSeamWeldType = ListLongSeamWeldTypeResourceIT.createEntity(em);
        em.persist(idLongSeamWeldType);
        em.flush();
        valveHist.setIdLongSeamWeldType(idLongSeamWeldType);
        valveHistRepository.saveAndFlush(valveHist);
        Long idLongSeamWeldTypeId = idLongSeamWeldType.getId();

        // Get all the valveHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId
        defaultValveHistShouldBeFound("idLongSeamWeldTypeId.equals=" + idLongSeamWeldTypeId);

        // Get all the valveHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId + 1
        defaultValveHistShouldNotBeFound("idLongSeamWeldTypeId.equals=" + (idLongSeamWeldTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdFabricationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListFabricationType idFabricationType = ListFabricationTypeResourceIT.createEntity(em);
        em.persist(idFabricationType);
        em.flush();
        valveHist.setIdFabricationType(idFabricationType);
        valveHistRepository.saveAndFlush(valveHist);
        Long idFabricationTypeId = idFabricationType.getId();

        // Get all the valveHistList where idFabricationType equals to idFabricationTypeId
        defaultValveHistShouldBeFound("idFabricationTypeId.equals=" + idFabricationTypeId);

        // Get all the valveHistList where idFabricationType equals to idFabricationTypeId + 1
        defaultValveHistShouldNotBeFound("idFabricationTypeId.equals=" + (idFabricationTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        valveHist.setIdMaterial(idMaterial);
        valveHistRepository.saveAndFlush(valveHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the valveHistList where idMaterial equals to idMaterialId
        defaultValveHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the valveHistList where idMaterial equals to idMaterialId + 1
        defaultValveHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdMillLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMillLocation idMillLocation = ListMillLocationResourceIT.createEntity(em);
        em.persist(idMillLocation);
        em.flush();
        valveHist.setIdMillLocation(idMillLocation);
        valveHistRepository.saveAndFlush(valveHist);
        Long idMillLocationId = idMillLocation.getId();

        // Get all the valveHistList where idMillLocation equals to idMillLocationId
        defaultValveHistShouldBeFound("idMillLocationId.equals=" + idMillLocationId);

        // Get all the valveHistList where idMillLocation equals to idMillLocationId + 1
        defaultValveHistShouldNotBeFound("idMillLocationId.equals=" + (idMillLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdSteelGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListSteelGrade idSteelGrade = ListSteelGradeResourceIT.createEntity(em);
        em.persist(idSteelGrade);
        em.flush();
        valveHist.setIdSteelGrade(idSteelGrade);
        valveHistRepository.saveAndFlush(valveHist);
        Long idSteelGradeId = idSteelGrade.getId();

        // Get all the valveHistList where idSteelGrade equals to idSteelGradeId
        defaultValveHistShouldBeFound("idSteelGradeId.equals=" + idSteelGradeId);

        // Get all the valveHistList where idSteelGrade equals to idSteelGradeId + 1
        defaultValveHistShouldNotBeFound("idSteelGradeId.equals=" + (idSteelGradeId + 1));
    }


    @Test
    @Transactional
    public void getAllValveHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = valveHist.getIdStatus();
        valveHistRepository.saveAndFlush(valveHist);
        Long idStatusId = idStatus.getId();

        // Get all the valveHistList where idStatus equals to idStatusId
        defaultValveHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the valveHistList where idStatus equals to idStatusId + 1
        defaultValveHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultValveHistShouldBeFound(String filter) throws Exception {
        restValveHistMockMvc.perform(get("/api/valve-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(valveHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].serialNum").value(hasItem(DEFAULT_SERIAL_NUM)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].diameterOuterSteel").value(hasItem(DEFAULT_DIAMETER_OUTER_STEEL)))
            .andExpect(jsonPath("$.[*].internalCoatThickness").value(hasItem(DEFAULT_INTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].externalCoatThickness").value(hasItem(DEFAULT_EXTERNAL_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].isConcrCoating").value(hasItem(DEFAULT_IS_CONCR_COATING)))
            .andExpect(jsonPath("$.[*].concrCoatThickness").value(hasItem(DEFAULT_CONCR_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].concrCoatDensity").value(hasItem(DEFAULT_CONCR_COAT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].measWallThickness").value(hasItem(DEFAULT_MEAS_WALL_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].smts").value(hasItem(DEFAULT_SMTS.intValue())))
            .andExpect(jsonPath("$.[*].smys").value(hasItem(DEFAULT_SMYS.intValue())))
            .andExpect(jsonPath("$.[*].sdbm").value(hasItem(DEFAULT_SDBM.intValue())))
            .andExpect(jsonPath("$.[*].sdaf").value(hasItem(DEFAULT_SDAF.intValue())))
            .andExpect(jsonPath("$.[*].sdcs").value(hasItem(DEFAULT_SDCS.intValue())))
            .andExpect(jsonPath("$.[*].allowTensStrain").value(hasItem(DEFAULT_ALLOW_TENS_STRAIN.intValue())))
            .andExpect(jsonPath("$.[*].corrosionAllowance").value(hasItem(DEFAULT_CORROSION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].fabricationAllowance").value(hasItem(DEFAULT_FABRICATION_ALLOWANCE)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressureIn").value(hasItem(DEFAULT_DESIGN_PRESSURE_IN.intValue())))
            .andExpect(jsonPath("$.[*].designPressureOut").value(hasItem(DEFAULT_DESIGN_PRESSURE_OUT.intValue())))
            .andExpect(jsonPath("$.[*].incidentalPressure").value(hasItem(DEFAULT_INCIDENTAL_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].dateInstalled").value(hasItem(DEFAULT_DATE_INSTALLED.toString())))
            .andExpect(jsonPath("$.[*].seamOrient").value(hasItem(DEFAULT_SEAM_ORIENT.intValue())))
            .andExpect(jsonPath("$.[*].seamAngle").value(hasItem(DEFAULT_SEAM_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].azimuth").value(hasItem(DEFAULT_AZIMUTH.intValue())))
            .andExpect(jsonPath("$.[*].seabInstallTemp").value(hasItem(DEFAULT_SEAB_INSTALL_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].verticalAngle").value(hasItem(DEFAULT_VERTICAL_ANGLE.intValue())))
            .andExpect(jsonPath("$.[*].heatTreatDurat").value(hasItem(DEFAULT_HEAT_TREAT_DURAT.intValue())))
            .andExpect(jsonPath("$.[*].maxDesignTemp").value(hasItem(DEFAULT_MAX_DESIGN_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].heatNumber").value(hasItem(DEFAULT_HEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restValveHistMockMvc.perform(get("/api/valve-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultValveHistShouldNotBeFound(String filter) throws Exception {
        restValveHistMockMvc.perform(get("/api/valve-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restValveHistMockMvc.perform(get("/api/valve-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingValveHist() throws Exception {
        // Get the valveHist
        restValveHistMockMvc.perform(get("/api/valve-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValveHist() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        int databaseSizeBeforeUpdate = valveHistRepository.findAll().size();

        // Update the valveHist
        ValveHist updatedValveHist = valveHistRepository.findById(valveHist.getId()).get();
        // Disconnect from session so that the updates on updatedValveHist are not directly saved in db
        em.detach(updatedValveHist);
        updatedValveHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .name(UPDATED_NAME)
            .serialNum(UPDATED_SERIAL_NUM)
            .model(UPDATED_MODEL)
            .diameterOuterSteel(UPDATED_DIAMETER_OUTER_STEEL)
            .internalCoatThickness(UPDATED_INTERNAL_COAT_THICKNESS)
            .externalCoatThickness(UPDATED_EXTERNAL_COAT_THICKNESS)
            .isConcrCoating(UPDATED_IS_CONCR_COATING)
            .concrCoatThickness(UPDATED_CONCR_COAT_THICKNESS)
            .concrCoatDensity(UPDATED_CONCR_COAT_DENSITY)
            .measWallThickness(UPDATED_MEAS_WALL_THICKNESS)
            .length(UPDATED_LENGTH)
            .weight(UPDATED_WEIGHT)
            .smts(UPDATED_SMTS)
            .smys(UPDATED_SMYS)
            .sdbm(UPDATED_SDBM)
            .sdaf(UPDATED_SDAF)
            .sdcs(UPDATED_SDCS)
            .allowTensStrain(UPDATED_ALLOW_TENS_STRAIN)
            .corrosionAllowance(UPDATED_CORROSION_ALLOWANCE)
            .fabricationAllowance(UPDATED_FABRICATION_ALLOWANCE)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
            .designPressureIn(UPDATED_DESIGN_PRESSURE_IN)
            .designPressureOut(UPDATED_DESIGN_PRESSURE_OUT)
            .incidentalPressure(UPDATED_INCIDENTAL_PRESSURE)
            .dateInstalled(UPDATED_DATE_INSTALLED)
            .seamOrient(UPDATED_SEAM_ORIENT)
            .seamAngle(UPDATED_SEAM_ANGLE)
            .azimuth(UPDATED_AZIMUTH)
            .seabInstallTemp(UPDATED_SEAB_INSTALL_TEMP)
            .verticalAngle(UPDATED_VERTICAL_ANGLE)
            .heatTreatDurat(UPDATED_HEAT_TREAT_DURAT)
            .maxDesignTemp(UPDATED_MAX_DESIGN_TEMP)
            .heatNumber(UPDATED_HEAT_NUMBER)
            .coord(UPDATED_COORD)
            .designCoord(UPDATED_DESIGN_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(updatedValveHist);

        restValveHistMockMvc.perform(put("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isOk());

        // Validate the ValveHist in the database
        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeUpdate);
        ValveHist testValveHist = valveHistList.get(valveHistList.size() - 1);
        assertThat(testValveHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testValveHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testValveHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testValveHist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testValveHist.getSerialNum()).isEqualTo(UPDATED_SERIAL_NUM);
        assertThat(testValveHist.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testValveHist.getDiameterOuterSteel()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL);
        assertThat(testValveHist.getInternalCoatThickness()).isEqualTo(UPDATED_INTERNAL_COAT_THICKNESS);
        assertThat(testValveHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testValveHist.getIsConcrCoating()).isEqualTo(UPDATED_IS_CONCR_COATING);
        assertThat(testValveHist.getConcrCoatThickness()).isEqualTo(UPDATED_CONCR_COAT_THICKNESS);
        assertThat(testValveHist.getConcrCoatDensity()).isEqualTo(UPDATED_CONCR_COAT_DENSITY);
        assertThat(testValveHist.getMeasWallThickness()).isEqualTo(UPDATED_MEAS_WALL_THICKNESS);
        assertThat(testValveHist.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testValveHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testValveHist.getSmts()).isEqualTo(UPDATED_SMTS);
        assertThat(testValveHist.getSmys()).isEqualTo(UPDATED_SMYS);
        assertThat(testValveHist.getSdbm()).isEqualTo(UPDATED_SDBM);
        assertThat(testValveHist.getSdaf()).isEqualTo(UPDATED_SDAF);
        assertThat(testValveHist.getSdcs()).isEqualTo(UPDATED_SDCS);
        assertThat(testValveHist.getAllowTensStrain()).isEqualTo(UPDATED_ALLOW_TENS_STRAIN);
        assertThat(testValveHist.getCorrosionAllowance()).isEqualTo(UPDATED_CORROSION_ALLOWANCE);
        assertThat(testValveHist.getFabricationAllowance()).isEqualTo(UPDATED_FABRICATION_ALLOWANCE);
        assertThat(testValveHist.getDateManufactured()).isEqualTo(UPDATED_DATE_MANUFACTURED);
        assertThat(testValveHist.getMillTestPressure()).isEqualTo(UPDATED_MILL_TEST_PRESSURE);
        assertThat(testValveHist.getDesignPressure()).isEqualTo(UPDATED_DESIGN_PRESSURE);
        assertThat(testValveHist.getDesignPressureIn()).isEqualTo(UPDATED_DESIGN_PRESSURE_IN);
        assertThat(testValveHist.getDesignPressureOut()).isEqualTo(UPDATED_DESIGN_PRESSURE_OUT);
        assertThat(testValveHist.getIncidentalPressure()).isEqualTo(UPDATED_INCIDENTAL_PRESSURE);
        assertThat(testValveHist.getDateInstalled()).isEqualTo(UPDATED_DATE_INSTALLED);
        assertThat(testValveHist.getSeamOrient()).isEqualTo(UPDATED_SEAM_ORIENT);
        assertThat(testValveHist.getSeamAngle()).isEqualTo(UPDATED_SEAM_ANGLE);
        assertThat(testValveHist.getAzimuth()).isEqualTo(UPDATED_AZIMUTH);
        assertThat(testValveHist.getSeabInstallTemp()).isEqualTo(UPDATED_SEAB_INSTALL_TEMP);
        assertThat(testValveHist.getVerticalAngle()).isEqualTo(UPDATED_VERTICAL_ANGLE);
        assertThat(testValveHist.getHeatTreatDurat()).isEqualTo(UPDATED_HEAT_TREAT_DURAT);
        assertThat(testValveHist.getMaxDesignTemp()).isEqualTo(UPDATED_MAX_DESIGN_TEMP);
        assertThat(testValveHist.getHeatNumber()).isEqualTo(UPDATED_HEAT_NUMBER);
        assertThat(testValveHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testValveHist.getDesignCoord()).isEqualTo(UPDATED_DESIGN_COORD);
        assertThat(testValveHist.getKpInst()).isEqualTo(UPDATED_KP_INST);
        assertThat(testValveHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testValveHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testValveHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testValveHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testValveHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testValveHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testValveHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingValveHist() throws Exception {
        int databaseSizeBeforeUpdate = valveHistRepository.findAll().size();

        // Create the ValveHist
        ValveHistDTO valveHistDTO = valveHistMapper.toDto(valveHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restValveHistMockMvc.perform(put("/api/valve-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(valveHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ValveHist in the database
        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteValveHist() throws Exception {
        // Initialize the database
        valveHistRepository.saveAndFlush(valveHist);

        int databaseSizeBeforeDelete = valveHistRepository.findAll().size();

        // Delete the valveHist
        restValveHistMockMvc.perform(delete("/api/valve-hists/{id}", valveHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ValveHist> valveHistList = valveHistRepository.findAll();
        assertThat(valveHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValveHist.class);
        ValveHist valveHist1 = new ValveHist();
        valveHist1.setId(1L);
        ValveHist valveHist2 = new ValveHist();
        valveHist2.setId(valveHist1.getId());
        assertThat(valveHist1).isEqualTo(valveHist2);
        valveHist2.setId(2L);
        assertThat(valveHist1).isNotEqualTo(valveHist2);
        valveHist1.setId(null);
        assertThat(valveHist1).isNotEqualTo(valveHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValveHistDTO.class);
        ValveHistDTO valveHistDTO1 = new ValveHistDTO();
        valveHistDTO1.setId(1L);
        ValveHistDTO valveHistDTO2 = new ValveHistDTO();
        assertThat(valveHistDTO1).isNotEqualTo(valveHistDTO2);
        valveHistDTO2.setId(valveHistDTO1.getId());
        assertThat(valveHistDTO1).isEqualTo(valveHistDTO2);
        valveHistDTO2.setId(2L);
        assertThat(valveHistDTO1).isNotEqualTo(valveHistDTO2);
        valveHistDTO1.setId(null);
        assertThat(valveHistDTO1).isNotEqualTo(valveHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(valveHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(valveHistMapper.fromId(null)).isNull();
    }
}
