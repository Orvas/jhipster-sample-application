package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.Pipe;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListPipeManufacturer;
import io.github.jhipster.application.domain.ListPipeSpecification;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.PipeHistRepository;
import io.github.jhipster.application.service.PipeHistService;
import io.github.jhipster.application.service.dto.PipeHistDTO;
import io.github.jhipster.application.service.mapper.PipeHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipeHistCriteria;
import io.github.jhipster.application.service.PipeHistQueryService;

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
 * Integration tests for the {@Link PipeHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipeHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_IS_BURIAL = 1;
    private static final Integer UPDATED_IS_BURIAL = 2;

    private static final Integer DEFAULT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_BURIAL_DEPTH = 2;

    private static final Integer DEFAULT_FACT_BURIAL_DEPTH = 1;
    private static final Integer UPDATED_FACT_BURIAL_DEPTH = 2;

    private static final LocalDate DEFAULT_DATE_MANUFACTURED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MANUFACTURED = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MILL_TEST_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_MILL_TEST_PRESSURE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_PRESSURE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_PRESSURE = new BigDecimal(2);

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

    private static final String DEFAULT_DESIGN_COORD = "AAAAAAAAAA";
    private static final String UPDATED_DESIGN_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

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
    private PipeHistRepository pipeHistRepository;

    @Autowired
    private PipeHistMapper pipeHistMapper;

    @Autowired
    private PipeHistService pipeHistService;

    @Autowired
    private PipeHistQueryService pipeHistQueryService;

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

    private MockMvc restPipeHistMockMvc;

    private PipeHist pipeHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipeHistResource pipeHistResource = new PipeHistResource(pipeHistService, pipeHistQueryService);
        this.restPipeHistMockMvc = MockMvcBuilders.standaloneSetup(pipeHistResource)
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
    public static PipeHist createEntity(EntityManager em) {
        PipeHist pipeHist = new PipeHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .num(DEFAULT_NUM)
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
            .isBurial(DEFAULT_IS_BURIAL)
            .burialDepth(DEFAULT_BURIAL_DEPTH)
            .factBurialDepth(DEFAULT_FACT_BURIAL_DEPTH)
            .dateManufactured(DEFAULT_DATE_MANUFACTURED)
            .millTestPressure(DEFAULT_MILL_TEST_PRESSURE)
            .designPressure(DEFAULT_DESIGN_PRESSURE)
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
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Pipe pipe;
        if (TestUtil.findAll(em, Pipe.class).isEmpty()) {
            pipe = PipeResourceIT.createEntity(em);
            em.persist(pipe);
            em.flush();
        } else {
            pipe = TestUtil.findAll(em, Pipe.class).get(0);
        }
        pipeHist.setId(pipe);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        pipeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipeHist.setIdStatus(listObjectStatus);
        return pipeHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PipeHist createUpdatedEntity(EntityManager em) {
        PipeHist pipeHist = new PipeHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .num(UPDATED_NUM)
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
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
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
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Pipe pipe;
        if (TestUtil.findAll(em, Pipe.class).isEmpty()) {
            pipe = PipeResourceIT.createUpdatedEntity(em);
            em.persist(pipe);
            em.flush();
        } else {
            pipe = TestUtil.findAll(em, Pipe.class).get(0);
        }
        pipeHist.setId(pipe);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        pipeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipeHist.setIdStatus(listObjectStatus);
        return pipeHist;
    }

    @BeforeEach
    public void initTest() {
        pipeHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipeHist() throws Exception {
        int databaseSizeBeforeCreate = pipeHistRepository.findAll().size();

        // Create the PipeHist
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);
        restPipeHistMockMvc.perform(post("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isCreated());

        // Validate the PipeHist in the database
        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeCreate + 1);
        PipeHist testPipeHist = pipeHistList.get(pipeHistList.size() - 1);
        assertThat(testPipeHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testPipeHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testPipeHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testPipeHist.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testPipeHist.getDiameterOuterSteel()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL);
        assertThat(testPipeHist.getInternalCoatThickness()).isEqualTo(DEFAULT_INTERNAL_COAT_THICKNESS);
        assertThat(testPipeHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testPipeHist.getIsConcrCoating()).isEqualTo(DEFAULT_IS_CONCR_COATING);
        assertThat(testPipeHist.getConcrCoatThickness()).isEqualTo(DEFAULT_CONCR_COAT_THICKNESS);
        assertThat(testPipeHist.getConcrCoatDensity()).isEqualTo(DEFAULT_CONCR_COAT_DENSITY);
        assertThat(testPipeHist.getMeasWallThickness()).isEqualTo(DEFAULT_MEAS_WALL_THICKNESS);
        assertThat(testPipeHist.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testPipeHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testPipeHist.getSmts()).isEqualTo(DEFAULT_SMTS);
        assertThat(testPipeHist.getSmys()).isEqualTo(DEFAULT_SMYS);
        assertThat(testPipeHist.getSdbm()).isEqualTo(DEFAULT_SDBM);
        assertThat(testPipeHist.getSdaf()).isEqualTo(DEFAULT_SDAF);
        assertThat(testPipeHist.getSdcs()).isEqualTo(DEFAULT_SDCS);
        assertThat(testPipeHist.getAllowTensStrain()).isEqualTo(DEFAULT_ALLOW_TENS_STRAIN);
        assertThat(testPipeHist.getCorrosionAllowance()).isEqualTo(DEFAULT_CORROSION_ALLOWANCE);
        assertThat(testPipeHist.getFabricationAllowance()).isEqualTo(DEFAULT_FABRICATION_ALLOWANCE);
        assertThat(testPipeHist.getIsBurial()).isEqualTo(DEFAULT_IS_BURIAL);
        assertThat(testPipeHist.getBurialDepth()).isEqualTo(DEFAULT_BURIAL_DEPTH);
        assertThat(testPipeHist.getFactBurialDepth()).isEqualTo(DEFAULT_FACT_BURIAL_DEPTH);
        assertThat(testPipeHist.getDateManufactured()).isEqualTo(DEFAULT_DATE_MANUFACTURED);
        assertThat(testPipeHist.getMillTestPressure()).isEqualTo(DEFAULT_MILL_TEST_PRESSURE);
        assertThat(testPipeHist.getDesignPressure()).isEqualTo(DEFAULT_DESIGN_PRESSURE);
        assertThat(testPipeHist.getIncidentalPressure()).isEqualTo(DEFAULT_INCIDENTAL_PRESSURE);
        assertThat(testPipeHist.getDateInstalled()).isEqualTo(DEFAULT_DATE_INSTALLED);
        assertThat(testPipeHist.getSeamOrient()).isEqualTo(DEFAULT_SEAM_ORIENT);
        assertThat(testPipeHist.getSeamAngle()).isEqualTo(DEFAULT_SEAM_ANGLE);
        assertThat(testPipeHist.getAzimuth()).isEqualTo(DEFAULT_AZIMUTH);
        assertThat(testPipeHist.getSeabInstallTemp()).isEqualTo(DEFAULT_SEAB_INSTALL_TEMP);
        assertThat(testPipeHist.getVerticalAngle()).isEqualTo(DEFAULT_VERTICAL_ANGLE);
        assertThat(testPipeHist.getHeatTreatDurat()).isEqualTo(DEFAULT_HEAT_TREAT_DURAT);
        assertThat(testPipeHist.getMaxDesignTemp()).isEqualTo(DEFAULT_MAX_DESIGN_TEMP);
        assertThat(testPipeHist.getHeatNumber()).isEqualTo(DEFAULT_HEAT_NUMBER);
        assertThat(testPipeHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testPipeHist.getDesignCoord()).isEqualTo(DEFAULT_DESIGN_COORD);
        assertThat(testPipeHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testPipeHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testPipeHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testPipeHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPipeHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testPipeHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipeHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipeHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipeHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipeHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipeHistRepository.findAll().size();

        // Create the PipeHist with an existing ID
        pipeHist.setId(1L);
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipeHistMockMvc.perform(post("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipeHist in the database
        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipeHistRepository.findAll().size();
        // set the field null
        pipeHist.setDateFrom(null);

        // Create the PipeHist, which fails.
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);

        restPipeHistMockMvc.perform(post("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipeHistRepository.findAll().size();
        // set the field null
        pipeHist.setDateTo(null);

        // Create the PipeHist, which fails.
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);

        restPipeHistMockMvc.perform(post("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipeHistRepository.findAll().size();
        // set the field null
        pipeHist.setIsCurrentFlag(null);

        // Create the PipeHist, which fails.
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);

        restPipeHistMockMvc.perform(post("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPipeHists() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList
        restPipeHistMockMvc.perform(get("/api/pipe-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
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
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
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
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.toString())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
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
    public void getPipeHist() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get the pipeHist
        restPipeHistMockMvc.perform(get("/api/pipe-hists/{id}", pipeHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipeHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
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
            .andExpect(jsonPath("$.isBurial").value(DEFAULT_IS_BURIAL))
            .andExpect(jsonPath("$.burialDepth").value(DEFAULT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.factBurialDepth").value(DEFAULT_FACT_BURIAL_DEPTH))
            .andExpect(jsonPath("$.dateManufactured").value(DEFAULT_DATE_MANUFACTURED.toString()))
            .andExpect(jsonPath("$.millTestPressure").value(DEFAULT_MILL_TEST_PRESSURE.intValue()))
            .andExpect(jsonPath("$.designPressure").value(DEFAULT_DESIGN_PRESSURE.intValue()))
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
            .andExpect(jsonPath("$.designCoord").value(DEFAULT_DESIGN_COORD.toString()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
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
    public void getAllPipeHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultPipeHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the pipeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipeHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultPipeHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the pipeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipeHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateFrom is not null
        defaultPipeHistShouldBeFound("dateFrom.specified=true");

        // Get all the pipeHistList where dateFrom is null
        defaultPipeHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultPipeHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the pipeHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultPipeHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultPipeHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the pipeHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultPipeHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateTo equals to DEFAULT_DATE_TO
        defaultPipeHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the pipeHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipeHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultPipeHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the pipeHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipeHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateTo is not null
        defaultPipeHistShouldBeFound("dateTo.specified=true");

        // Get all the pipeHistList where dateTo is null
        defaultPipeHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultPipeHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the pipeHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultPipeHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultPipeHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the pipeHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultPipeHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where idWrk equals to DEFAULT_ID_WRK
        defaultPipeHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the pipeHistList where idWrk equals to UPDATED_ID_WRK
        defaultPipeHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultPipeHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the pipeHistList where idWrk equals to UPDATED_ID_WRK
        defaultPipeHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where idWrk is not null
        defaultPipeHistShouldBeFound("idWrk.specified=true");

        // Get all the pipeHistList where idWrk is null
        defaultPipeHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultPipeHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the pipeHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultPipeHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultPipeHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the pipeHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultPipeHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByNumIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where num equals to DEFAULT_NUM
        defaultPipeHistShouldBeFound("num.equals=" + DEFAULT_NUM);

        // Get all the pipeHistList where num equals to UPDATED_NUM
        defaultPipeHistShouldNotBeFound("num.equals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByNumIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where num in DEFAULT_NUM or UPDATED_NUM
        defaultPipeHistShouldBeFound("num.in=" + DEFAULT_NUM + "," + UPDATED_NUM);

        // Get all the pipeHistList where num equals to UPDATED_NUM
        defaultPipeHistShouldNotBeFound("num.in=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where num is not null
        defaultPipeHistShouldBeFound("num.specified=true");

        // Get all the pipeHistList where num is null
        defaultPipeHistShouldNotBeFound("num.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDiameterOuterSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where diameterOuterSteel equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldBeFound("diameterOuterSteel.equals=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the pipeHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldNotBeFound("diameterOuterSteel.equals=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDiameterOuterSteelIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where diameterOuterSteel in DEFAULT_DIAMETER_OUTER_STEEL or UPDATED_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldBeFound("diameterOuterSteel.in=" + DEFAULT_DIAMETER_OUTER_STEEL + "," + UPDATED_DIAMETER_OUTER_STEEL);

        // Get all the pipeHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldNotBeFound("diameterOuterSteel.in=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDiameterOuterSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where diameterOuterSteel is not null
        defaultPipeHistShouldBeFound("diameterOuterSteel.specified=true");

        // Get all the pipeHistList where diameterOuterSteel is null
        defaultPipeHistShouldNotBeFound("diameterOuterSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDiameterOuterSteelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where diameterOuterSteel greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldBeFound("diameterOuterSteel.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the pipeHistList where diameterOuterSteel greater than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldNotBeFound("diameterOuterSteel.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDiameterOuterSteelIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where diameterOuterSteel less than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldNotBeFound("diameterOuterSteel.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the pipeHistList where diameterOuterSteel less than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultPipeHistShouldBeFound("diameterOuterSteel.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByInternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where internalCoatThickness equals to DEFAULT_INTERNAL_COAT_THICKNESS
        defaultPipeHistShouldBeFound("internalCoatThickness.equals=" + DEFAULT_INTERNAL_COAT_THICKNESS);

        // Get all the pipeHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("internalCoatThickness.equals=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByInternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where internalCoatThickness in DEFAULT_INTERNAL_COAT_THICKNESS or UPDATED_INTERNAL_COAT_THICKNESS
        defaultPipeHistShouldBeFound("internalCoatThickness.in=" + DEFAULT_INTERNAL_COAT_THICKNESS + "," + UPDATED_INTERNAL_COAT_THICKNESS);

        // Get all the pipeHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("internalCoatThickness.in=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByInternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where internalCoatThickness is not null
        defaultPipeHistShouldBeFound("internalCoatThickness.specified=true");

        // Get all the pipeHistList where internalCoatThickness is null
        defaultPipeHistShouldNotBeFound("internalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultPipeHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the pipeHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipeHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the pipeHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where externalCoatThickness is not null
        defaultPipeHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the pipeHistList where externalCoatThickness is null
        defaultPipeHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsConcrCoatingIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isConcrCoating equals to DEFAULT_IS_CONCR_COATING
        defaultPipeHistShouldBeFound("isConcrCoating.equals=" + DEFAULT_IS_CONCR_COATING);

        // Get all the pipeHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultPipeHistShouldNotBeFound("isConcrCoating.equals=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsConcrCoatingIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isConcrCoating in DEFAULT_IS_CONCR_COATING or UPDATED_IS_CONCR_COATING
        defaultPipeHistShouldBeFound("isConcrCoating.in=" + DEFAULT_IS_CONCR_COATING + "," + UPDATED_IS_CONCR_COATING);

        // Get all the pipeHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultPipeHistShouldNotBeFound("isConcrCoating.in=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsConcrCoatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isConcrCoating is not null
        defaultPipeHistShouldBeFound("isConcrCoating.specified=true");

        // Get all the pipeHistList where isConcrCoating is null
        defaultPipeHistShouldNotBeFound("isConcrCoating.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsConcrCoatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isConcrCoating greater than or equals to DEFAULT_IS_CONCR_COATING
        defaultPipeHistShouldBeFound("isConcrCoating.greaterOrEqualThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the pipeHistList where isConcrCoating greater than or equals to UPDATED_IS_CONCR_COATING
        defaultPipeHistShouldNotBeFound("isConcrCoating.greaterOrEqualThan=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsConcrCoatingIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isConcrCoating less than or equals to DEFAULT_IS_CONCR_COATING
        defaultPipeHistShouldNotBeFound("isConcrCoating.lessThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the pipeHistList where isConcrCoating less than or equals to UPDATED_IS_CONCR_COATING
        defaultPipeHistShouldBeFound("isConcrCoating.lessThan=" + UPDATED_IS_CONCR_COATING);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatThickness equals to DEFAULT_CONCR_COAT_THICKNESS
        defaultPipeHistShouldBeFound("concrCoatThickness.equals=" + DEFAULT_CONCR_COAT_THICKNESS);

        // Get all the pipeHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("concrCoatThickness.equals=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatThickness in DEFAULT_CONCR_COAT_THICKNESS or UPDATED_CONCR_COAT_THICKNESS
        defaultPipeHistShouldBeFound("concrCoatThickness.in=" + DEFAULT_CONCR_COAT_THICKNESS + "," + UPDATED_CONCR_COAT_THICKNESS);

        // Get all the pipeHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultPipeHistShouldNotBeFound("concrCoatThickness.in=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatThickness is not null
        defaultPipeHistShouldBeFound("concrCoatThickness.specified=true");

        // Get all the pipeHistList where concrCoatThickness is null
        defaultPipeHistShouldNotBeFound("concrCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatDensity equals to DEFAULT_CONCR_COAT_DENSITY
        defaultPipeHistShouldBeFound("concrCoatDensity.equals=" + DEFAULT_CONCR_COAT_DENSITY);

        // Get all the pipeHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultPipeHistShouldNotBeFound("concrCoatDensity.equals=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatDensityIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatDensity in DEFAULT_CONCR_COAT_DENSITY or UPDATED_CONCR_COAT_DENSITY
        defaultPipeHistShouldBeFound("concrCoatDensity.in=" + DEFAULT_CONCR_COAT_DENSITY + "," + UPDATED_CONCR_COAT_DENSITY);

        // Get all the pipeHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultPipeHistShouldNotBeFound("concrCoatDensity.in=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByConcrCoatDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where concrCoatDensity is not null
        defaultPipeHistShouldBeFound("concrCoatDensity.specified=true");

        // Get all the pipeHistList where concrCoatDensity is null
        defaultPipeHistShouldNotBeFound("concrCoatDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMeasWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where measWallThickness equals to DEFAULT_MEAS_WALL_THICKNESS
        defaultPipeHistShouldBeFound("measWallThickness.equals=" + DEFAULT_MEAS_WALL_THICKNESS);

        // Get all the pipeHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultPipeHistShouldNotBeFound("measWallThickness.equals=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMeasWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where measWallThickness in DEFAULT_MEAS_WALL_THICKNESS or UPDATED_MEAS_WALL_THICKNESS
        defaultPipeHistShouldBeFound("measWallThickness.in=" + DEFAULT_MEAS_WALL_THICKNESS + "," + UPDATED_MEAS_WALL_THICKNESS);

        // Get all the pipeHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultPipeHistShouldNotBeFound("measWallThickness.in=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMeasWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where measWallThickness is not null
        defaultPipeHistShouldBeFound("measWallThickness.specified=true");

        // Get all the pipeHistList where measWallThickness is null
        defaultPipeHistShouldNotBeFound("measWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where length equals to DEFAULT_LENGTH
        defaultPipeHistShouldBeFound("length.equals=" + DEFAULT_LENGTH);

        // Get all the pipeHistList where length equals to UPDATED_LENGTH
        defaultPipeHistShouldNotBeFound("length.equals=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByLengthIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where length in DEFAULT_LENGTH or UPDATED_LENGTH
        defaultPipeHistShouldBeFound("length.in=" + DEFAULT_LENGTH + "," + UPDATED_LENGTH);

        // Get all the pipeHistList where length equals to UPDATED_LENGTH
        defaultPipeHistShouldNotBeFound("length.in=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where length is not null
        defaultPipeHistShouldBeFound("length.specified=true");

        // Get all the pipeHistList where length is null
        defaultPipeHistShouldNotBeFound("length.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where length greater than or equals to DEFAULT_LENGTH
        defaultPipeHistShouldBeFound("length.greaterOrEqualThan=" + DEFAULT_LENGTH);

        // Get all the pipeHistList where length greater than or equals to UPDATED_LENGTH
        defaultPipeHistShouldNotBeFound("length.greaterOrEqualThan=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where length less than or equals to DEFAULT_LENGTH
        defaultPipeHistShouldNotBeFound("length.lessThan=" + DEFAULT_LENGTH);

        // Get all the pipeHistList where length less than or equals to UPDATED_LENGTH
        defaultPipeHistShouldBeFound("length.lessThan=" + UPDATED_LENGTH);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where weight equals to DEFAULT_WEIGHT
        defaultPipeHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the pipeHistList where weight equals to UPDATED_WEIGHT
        defaultPipeHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultPipeHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the pipeHistList where weight equals to UPDATED_WEIGHT
        defaultPipeHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where weight is not null
        defaultPipeHistShouldBeFound("weight.specified=true");

        // Get all the pipeHistList where weight is null
        defaultPipeHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmtsIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smts equals to DEFAULT_SMTS
        defaultPipeHistShouldBeFound("smts.equals=" + DEFAULT_SMTS);

        // Get all the pipeHistList where smts equals to UPDATED_SMTS
        defaultPipeHistShouldNotBeFound("smts.equals=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmtsIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smts in DEFAULT_SMTS or UPDATED_SMTS
        defaultPipeHistShouldBeFound("smts.in=" + DEFAULT_SMTS + "," + UPDATED_SMTS);

        // Get all the pipeHistList where smts equals to UPDATED_SMTS
        defaultPipeHistShouldNotBeFound("smts.in=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmtsIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smts is not null
        defaultPipeHistShouldBeFound("smts.specified=true");

        // Get all the pipeHistList where smts is null
        defaultPipeHistShouldNotBeFound("smts.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmysIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smys equals to DEFAULT_SMYS
        defaultPipeHistShouldBeFound("smys.equals=" + DEFAULT_SMYS);

        // Get all the pipeHistList where smys equals to UPDATED_SMYS
        defaultPipeHistShouldNotBeFound("smys.equals=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmysIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smys in DEFAULT_SMYS or UPDATED_SMYS
        defaultPipeHistShouldBeFound("smys.in=" + DEFAULT_SMYS + "," + UPDATED_SMYS);

        // Get all the pipeHistList where smys equals to UPDATED_SMYS
        defaultPipeHistShouldNotBeFound("smys.in=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySmysIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where smys is not null
        defaultPipeHistShouldBeFound("smys.specified=true");

        // Get all the pipeHistList where smys is null
        defaultPipeHistShouldNotBeFound("smys.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdbmIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdbm equals to DEFAULT_SDBM
        defaultPipeHistShouldBeFound("sdbm.equals=" + DEFAULT_SDBM);

        // Get all the pipeHistList where sdbm equals to UPDATED_SDBM
        defaultPipeHistShouldNotBeFound("sdbm.equals=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdbmIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdbm in DEFAULT_SDBM or UPDATED_SDBM
        defaultPipeHistShouldBeFound("sdbm.in=" + DEFAULT_SDBM + "," + UPDATED_SDBM);

        // Get all the pipeHistList where sdbm equals to UPDATED_SDBM
        defaultPipeHistShouldNotBeFound("sdbm.in=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdbmIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdbm is not null
        defaultPipeHistShouldBeFound("sdbm.specified=true");

        // Get all the pipeHistList where sdbm is null
        defaultPipeHistShouldNotBeFound("sdbm.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdafIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdaf equals to DEFAULT_SDAF
        defaultPipeHistShouldBeFound("sdaf.equals=" + DEFAULT_SDAF);

        // Get all the pipeHistList where sdaf equals to UPDATED_SDAF
        defaultPipeHistShouldNotBeFound("sdaf.equals=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdafIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdaf in DEFAULT_SDAF or UPDATED_SDAF
        defaultPipeHistShouldBeFound("sdaf.in=" + DEFAULT_SDAF + "," + UPDATED_SDAF);

        // Get all the pipeHistList where sdaf equals to UPDATED_SDAF
        defaultPipeHistShouldNotBeFound("sdaf.in=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdafIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdaf is not null
        defaultPipeHistShouldBeFound("sdaf.specified=true");

        // Get all the pipeHistList where sdaf is null
        defaultPipeHistShouldNotBeFound("sdaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdcsIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdcs equals to DEFAULT_SDCS
        defaultPipeHistShouldBeFound("sdcs.equals=" + DEFAULT_SDCS);

        // Get all the pipeHistList where sdcs equals to UPDATED_SDCS
        defaultPipeHistShouldNotBeFound("sdcs.equals=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdcsIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdcs in DEFAULT_SDCS or UPDATED_SDCS
        defaultPipeHistShouldBeFound("sdcs.in=" + DEFAULT_SDCS + "," + UPDATED_SDCS);

        // Get all the pipeHistList where sdcs equals to UPDATED_SDCS
        defaultPipeHistShouldNotBeFound("sdcs.in=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySdcsIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where sdcs is not null
        defaultPipeHistShouldBeFound("sdcs.specified=true");

        // Get all the pipeHistList where sdcs is null
        defaultPipeHistShouldNotBeFound("sdcs.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAllowTensStrainIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where allowTensStrain equals to DEFAULT_ALLOW_TENS_STRAIN
        defaultPipeHistShouldBeFound("allowTensStrain.equals=" + DEFAULT_ALLOW_TENS_STRAIN);

        // Get all the pipeHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultPipeHistShouldNotBeFound("allowTensStrain.equals=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAllowTensStrainIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where allowTensStrain in DEFAULT_ALLOW_TENS_STRAIN or UPDATED_ALLOW_TENS_STRAIN
        defaultPipeHistShouldBeFound("allowTensStrain.in=" + DEFAULT_ALLOW_TENS_STRAIN + "," + UPDATED_ALLOW_TENS_STRAIN);

        // Get all the pipeHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultPipeHistShouldNotBeFound("allowTensStrain.in=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAllowTensStrainIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where allowTensStrain is not null
        defaultPipeHistShouldBeFound("allowTensStrain.specified=true");

        // Get all the pipeHistList where allowTensStrain is null
        defaultPipeHistShouldNotBeFound("allowTensStrain.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCorrosionAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where corrosionAllowance equals to DEFAULT_CORROSION_ALLOWANCE
        defaultPipeHistShouldBeFound("corrosionAllowance.equals=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the pipeHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("corrosionAllowance.equals=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCorrosionAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where corrosionAllowance in DEFAULT_CORROSION_ALLOWANCE or UPDATED_CORROSION_ALLOWANCE
        defaultPipeHistShouldBeFound("corrosionAllowance.in=" + DEFAULT_CORROSION_ALLOWANCE + "," + UPDATED_CORROSION_ALLOWANCE);

        // Get all the pipeHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("corrosionAllowance.in=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCorrosionAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where corrosionAllowance is not null
        defaultPipeHistShouldBeFound("corrosionAllowance.specified=true");

        // Get all the pipeHistList where corrosionAllowance is null
        defaultPipeHistShouldNotBeFound("corrosionAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCorrosionAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where corrosionAllowance greater than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultPipeHistShouldBeFound("corrosionAllowance.greaterOrEqualThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the pipeHistList where corrosionAllowance greater than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("corrosionAllowance.greaterOrEqualThan=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCorrosionAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where corrosionAllowance less than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("corrosionAllowance.lessThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the pipeHistList where corrosionAllowance less than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultPipeHistShouldBeFound("corrosionAllowance.lessThan=" + UPDATED_CORROSION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByFabricationAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where fabricationAllowance equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultPipeHistShouldBeFound("fabricationAllowance.equals=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the pipeHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("fabricationAllowance.equals=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFabricationAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where fabricationAllowance in DEFAULT_FABRICATION_ALLOWANCE or UPDATED_FABRICATION_ALLOWANCE
        defaultPipeHistShouldBeFound("fabricationAllowance.in=" + DEFAULT_FABRICATION_ALLOWANCE + "," + UPDATED_FABRICATION_ALLOWANCE);

        // Get all the pipeHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("fabricationAllowance.in=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFabricationAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where fabricationAllowance is not null
        defaultPipeHistShouldBeFound("fabricationAllowance.specified=true");

        // Get all the pipeHistList where fabricationAllowance is null
        defaultPipeHistShouldNotBeFound("fabricationAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFabricationAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where fabricationAllowance greater than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultPipeHistShouldBeFound("fabricationAllowance.greaterOrEqualThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the pipeHistList where fabricationAllowance greater than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("fabricationAllowance.greaterOrEqualThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFabricationAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where fabricationAllowance less than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultPipeHistShouldNotBeFound("fabricationAllowance.lessThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the pipeHistList where fabricationAllowance less than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultPipeHistShouldBeFound("fabricationAllowance.lessThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIsBurialIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isBurial equals to DEFAULT_IS_BURIAL
        defaultPipeHistShouldBeFound("isBurial.equals=" + DEFAULT_IS_BURIAL);

        // Get all the pipeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultPipeHistShouldNotBeFound("isBurial.equals=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsBurialIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isBurial in DEFAULT_IS_BURIAL or UPDATED_IS_BURIAL
        defaultPipeHistShouldBeFound("isBurial.in=" + DEFAULT_IS_BURIAL + "," + UPDATED_IS_BURIAL);

        // Get all the pipeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultPipeHistShouldNotBeFound("isBurial.in=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsBurialIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isBurial is not null
        defaultPipeHistShouldBeFound("isBurial.specified=true");

        // Get all the pipeHistList where isBurial is null
        defaultPipeHistShouldNotBeFound("isBurial.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsBurialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isBurial greater than or equals to DEFAULT_IS_BURIAL
        defaultPipeHistShouldBeFound("isBurial.greaterOrEqualThan=" + DEFAULT_IS_BURIAL);

        // Get all the pipeHistList where isBurial greater than or equals to UPDATED_IS_BURIAL
        defaultPipeHistShouldNotBeFound("isBurial.greaterOrEqualThan=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsBurialIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isBurial less than or equals to DEFAULT_IS_BURIAL
        defaultPipeHistShouldNotBeFound("isBurial.lessThan=" + DEFAULT_IS_BURIAL);

        // Get all the pipeHistList where isBurial less than or equals to UPDATED_IS_BURIAL
        defaultPipeHistShouldBeFound("isBurial.lessThan=" + UPDATED_IS_BURIAL);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where burialDepth equals to DEFAULT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("burialDepth.equals=" + DEFAULT_BURIAL_DEPTH);

        // Get all the pipeHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("burialDepth.equals=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where burialDepth in DEFAULT_BURIAL_DEPTH or UPDATED_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("burialDepth.in=" + DEFAULT_BURIAL_DEPTH + "," + UPDATED_BURIAL_DEPTH);

        // Get all the pipeHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("burialDepth.in=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where burialDepth is not null
        defaultPipeHistShouldBeFound("burialDepth.specified=true");

        // Get all the pipeHistList where burialDepth is null
        defaultPipeHistShouldNotBeFound("burialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where burialDepth greater than or equals to DEFAULT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("burialDepth.greaterOrEqualThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the pipeHistList where burialDepth greater than or equals to UPDATED_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("burialDepth.greaterOrEqualThan=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where burialDepth less than or equals to DEFAULT_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("burialDepth.lessThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the pipeHistList where burialDepth less than or equals to UPDATED_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("burialDepth.lessThan=" + UPDATED_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByFactBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where factBurialDepth equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("factBurialDepth.equals=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the pipeHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("factBurialDepth.equals=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFactBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where factBurialDepth in DEFAULT_FACT_BURIAL_DEPTH or UPDATED_FACT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("factBurialDepth.in=" + DEFAULT_FACT_BURIAL_DEPTH + "," + UPDATED_FACT_BURIAL_DEPTH);

        // Get all the pipeHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("factBurialDepth.in=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFactBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where factBurialDepth is not null
        defaultPipeHistShouldBeFound("factBurialDepth.specified=true");

        // Get all the pipeHistList where factBurialDepth is null
        defaultPipeHistShouldNotBeFound("factBurialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFactBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where factBurialDepth greater than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("factBurialDepth.greaterOrEqualThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the pipeHistList where factBurialDepth greater than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("factBurialDepth.greaterOrEqualThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByFactBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where factBurialDepth less than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultPipeHistShouldNotBeFound("factBurialDepth.lessThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the pipeHistList where factBurialDepth less than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultPipeHistShouldBeFound("factBurialDepth.lessThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByDateManufacturedIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateManufactured equals to DEFAULT_DATE_MANUFACTURED
        defaultPipeHistShouldBeFound("dateManufactured.equals=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the pipeHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultPipeHistShouldNotBeFound("dateManufactured.equals=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateManufacturedIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateManufactured in DEFAULT_DATE_MANUFACTURED or UPDATED_DATE_MANUFACTURED
        defaultPipeHistShouldBeFound("dateManufactured.in=" + DEFAULT_DATE_MANUFACTURED + "," + UPDATED_DATE_MANUFACTURED);

        // Get all the pipeHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultPipeHistShouldNotBeFound("dateManufactured.in=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateManufacturedIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateManufactured is not null
        defaultPipeHistShouldBeFound("dateManufactured.specified=true");

        // Get all the pipeHistList where dateManufactured is null
        defaultPipeHistShouldNotBeFound("dateManufactured.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateManufacturedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateManufactured greater than or equals to DEFAULT_DATE_MANUFACTURED
        defaultPipeHistShouldBeFound("dateManufactured.greaterOrEqualThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the pipeHistList where dateManufactured greater than or equals to UPDATED_DATE_MANUFACTURED
        defaultPipeHistShouldNotBeFound("dateManufactured.greaterOrEqualThan=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateManufacturedIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateManufactured less than or equals to DEFAULT_DATE_MANUFACTURED
        defaultPipeHistShouldNotBeFound("dateManufactured.lessThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the pipeHistList where dateManufactured less than or equals to UPDATED_DATE_MANUFACTURED
        defaultPipeHistShouldBeFound("dateManufactured.lessThan=" + UPDATED_DATE_MANUFACTURED);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByMillTestPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where millTestPressure equals to DEFAULT_MILL_TEST_PRESSURE
        defaultPipeHistShouldBeFound("millTestPressure.equals=" + DEFAULT_MILL_TEST_PRESSURE);

        // Get all the pipeHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultPipeHistShouldNotBeFound("millTestPressure.equals=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMillTestPressureIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where millTestPressure in DEFAULT_MILL_TEST_PRESSURE or UPDATED_MILL_TEST_PRESSURE
        defaultPipeHistShouldBeFound("millTestPressure.in=" + DEFAULT_MILL_TEST_PRESSURE + "," + UPDATED_MILL_TEST_PRESSURE);

        // Get all the pipeHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultPipeHistShouldNotBeFound("millTestPressure.in=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMillTestPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where millTestPressure is not null
        defaultPipeHistShouldBeFound("millTestPressure.specified=true");

        // Get all the pipeHistList where millTestPressure is null
        defaultPipeHistShouldNotBeFound("millTestPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designPressure equals to DEFAULT_DESIGN_PRESSURE
        defaultPipeHistShouldBeFound("designPressure.equals=" + DEFAULT_DESIGN_PRESSURE);

        // Get all the pipeHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultPipeHistShouldNotBeFound("designPressure.equals=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignPressureIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designPressure in DEFAULT_DESIGN_PRESSURE or UPDATED_DESIGN_PRESSURE
        defaultPipeHistShouldBeFound("designPressure.in=" + DEFAULT_DESIGN_PRESSURE + "," + UPDATED_DESIGN_PRESSURE);

        // Get all the pipeHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultPipeHistShouldNotBeFound("designPressure.in=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designPressure is not null
        defaultPipeHistShouldBeFound("designPressure.specified=true");

        // Get all the pipeHistList where designPressure is null
        defaultPipeHistShouldNotBeFound("designPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIncidentalPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where incidentalPressure equals to DEFAULT_INCIDENTAL_PRESSURE
        defaultPipeHistShouldBeFound("incidentalPressure.equals=" + DEFAULT_INCIDENTAL_PRESSURE);

        // Get all the pipeHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultPipeHistShouldNotBeFound("incidentalPressure.equals=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIncidentalPressureIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where incidentalPressure in DEFAULT_INCIDENTAL_PRESSURE or UPDATED_INCIDENTAL_PRESSURE
        defaultPipeHistShouldBeFound("incidentalPressure.in=" + DEFAULT_INCIDENTAL_PRESSURE + "," + UPDATED_INCIDENTAL_PRESSURE);

        // Get all the pipeHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultPipeHistShouldNotBeFound("incidentalPressure.in=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIncidentalPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where incidentalPressure is not null
        defaultPipeHistShouldBeFound("incidentalPressure.specified=true");

        // Get all the pipeHistList where incidentalPressure is null
        defaultPipeHistShouldNotBeFound("incidentalPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateInstalledIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateInstalled equals to DEFAULT_DATE_INSTALLED
        defaultPipeHistShouldBeFound("dateInstalled.equals=" + DEFAULT_DATE_INSTALLED);

        // Get all the pipeHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultPipeHistShouldNotBeFound("dateInstalled.equals=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateInstalledIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateInstalled in DEFAULT_DATE_INSTALLED or UPDATED_DATE_INSTALLED
        defaultPipeHistShouldBeFound("dateInstalled.in=" + DEFAULT_DATE_INSTALLED + "," + UPDATED_DATE_INSTALLED);

        // Get all the pipeHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultPipeHistShouldNotBeFound("dateInstalled.in=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateInstalledIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateInstalled is not null
        defaultPipeHistShouldBeFound("dateInstalled.specified=true");

        // Get all the pipeHistList where dateInstalled is null
        defaultPipeHistShouldNotBeFound("dateInstalled.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateInstalledIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateInstalled greater than or equals to DEFAULT_DATE_INSTALLED
        defaultPipeHistShouldBeFound("dateInstalled.greaterOrEqualThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the pipeHistList where dateInstalled greater than or equals to UPDATED_DATE_INSTALLED
        defaultPipeHistShouldNotBeFound("dateInstalled.greaterOrEqualThan=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateInstalledIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateInstalled less than or equals to DEFAULT_DATE_INSTALLED
        defaultPipeHistShouldNotBeFound("dateInstalled.lessThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the pipeHistList where dateInstalled less than or equals to UPDATED_DATE_INSTALLED
        defaultPipeHistShouldBeFound("dateInstalled.lessThan=" + UPDATED_DATE_INSTALLED);
    }


    @Test
    @Transactional
    public void getAllPipeHistsBySeamOrientIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamOrient equals to DEFAULT_SEAM_ORIENT
        defaultPipeHistShouldBeFound("seamOrient.equals=" + DEFAULT_SEAM_ORIENT);

        // Get all the pipeHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultPipeHistShouldNotBeFound("seamOrient.equals=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeamOrientIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamOrient in DEFAULT_SEAM_ORIENT or UPDATED_SEAM_ORIENT
        defaultPipeHistShouldBeFound("seamOrient.in=" + DEFAULT_SEAM_ORIENT + "," + UPDATED_SEAM_ORIENT);

        // Get all the pipeHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultPipeHistShouldNotBeFound("seamOrient.in=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeamOrientIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamOrient is not null
        defaultPipeHistShouldBeFound("seamOrient.specified=true");

        // Get all the pipeHistList where seamOrient is null
        defaultPipeHistShouldNotBeFound("seamOrient.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeamAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamAngle equals to DEFAULT_SEAM_ANGLE
        defaultPipeHistShouldBeFound("seamAngle.equals=" + DEFAULT_SEAM_ANGLE);

        // Get all the pipeHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultPipeHistShouldNotBeFound("seamAngle.equals=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeamAngleIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamAngle in DEFAULT_SEAM_ANGLE or UPDATED_SEAM_ANGLE
        defaultPipeHistShouldBeFound("seamAngle.in=" + DEFAULT_SEAM_ANGLE + "," + UPDATED_SEAM_ANGLE);

        // Get all the pipeHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultPipeHistShouldNotBeFound("seamAngle.in=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeamAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seamAngle is not null
        defaultPipeHistShouldBeFound("seamAngle.specified=true");

        // Get all the pipeHistList where seamAngle is null
        defaultPipeHistShouldNotBeFound("seamAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAzimuthIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where azimuth equals to DEFAULT_AZIMUTH
        defaultPipeHistShouldBeFound("azimuth.equals=" + DEFAULT_AZIMUTH);

        // Get all the pipeHistList where azimuth equals to UPDATED_AZIMUTH
        defaultPipeHistShouldNotBeFound("azimuth.equals=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAzimuthIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where azimuth in DEFAULT_AZIMUTH or UPDATED_AZIMUTH
        defaultPipeHistShouldBeFound("azimuth.in=" + DEFAULT_AZIMUTH + "," + UPDATED_AZIMUTH);

        // Get all the pipeHistList where azimuth equals to UPDATED_AZIMUTH
        defaultPipeHistShouldNotBeFound("azimuth.in=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByAzimuthIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where azimuth is not null
        defaultPipeHistShouldBeFound("azimuth.specified=true");

        // Get all the pipeHistList where azimuth is null
        defaultPipeHistShouldNotBeFound("azimuth.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeabInstallTempIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seabInstallTemp equals to DEFAULT_SEAB_INSTALL_TEMP
        defaultPipeHistShouldBeFound("seabInstallTemp.equals=" + DEFAULT_SEAB_INSTALL_TEMP);

        // Get all the pipeHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultPipeHistShouldNotBeFound("seabInstallTemp.equals=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeabInstallTempIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seabInstallTemp in DEFAULT_SEAB_INSTALL_TEMP or UPDATED_SEAB_INSTALL_TEMP
        defaultPipeHistShouldBeFound("seabInstallTemp.in=" + DEFAULT_SEAB_INSTALL_TEMP + "," + UPDATED_SEAB_INSTALL_TEMP);

        // Get all the pipeHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultPipeHistShouldNotBeFound("seabInstallTemp.in=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllPipeHistsBySeabInstallTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where seabInstallTemp is not null
        defaultPipeHistShouldBeFound("seabInstallTemp.specified=true");

        // Get all the pipeHistList where seabInstallTemp is null
        defaultPipeHistShouldNotBeFound("seabInstallTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByVerticalAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where verticalAngle equals to DEFAULT_VERTICAL_ANGLE
        defaultPipeHistShouldBeFound("verticalAngle.equals=" + DEFAULT_VERTICAL_ANGLE);

        // Get all the pipeHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultPipeHistShouldNotBeFound("verticalAngle.equals=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByVerticalAngleIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where verticalAngle in DEFAULT_VERTICAL_ANGLE or UPDATED_VERTICAL_ANGLE
        defaultPipeHistShouldBeFound("verticalAngle.in=" + DEFAULT_VERTICAL_ANGLE + "," + UPDATED_VERTICAL_ANGLE);

        // Get all the pipeHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultPipeHistShouldNotBeFound("verticalAngle.in=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByVerticalAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where verticalAngle is not null
        defaultPipeHistShouldBeFound("verticalAngle.specified=true");

        // Get all the pipeHistList where verticalAngle is null
        defaultPipeHistShouldNotBeFound("verticalAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatTreatDuratIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatTreatDurat equals to DEFAULT_HEAT_TREAT_DURAT
        defaultPipeHistShouldBeFound("heatTreatDurat.equals=" + DEFAULT_HEAT_TREAT_DURAT);

        // Get all the pipeHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultPipeHistShouldNotBeFound("heatTreatDurat.equals=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatTreatDuratIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatTreatDurat in DEFAULT_HEAT_TREAT_DURAT or UPDATED_HEAT_TREAT_DURAT
        defaultPipeHistShouldBeFound("heatTreatDurat.in=" + DEFAULT_HEAT_TREAT_DURAT + "," + UPDATED_HEAT_TREAT_DURAT);

        // Get all the pipeHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultPipeHistShouldNotBeFound("heatTreatDurat.in=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatTreatDuratIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatTreatDurat is not null
        defaultPipeHistShouldBeFound("heatTreatDurat.specified=true");

        // Get all the pipeHistList where heatTreatDurat is null
        defaultPipeHistShouldNotBeFound("heatTreatDurat.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMaxDesignTempIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where maxDesignTemp equals to DEFAULT_MAX_DESIGN_TEMP
        defaultPipeHistShouldBeFound("maxDesignTemp.equals=" + DEFAULT_MAX_DESIGN_TEMP);

        // Get all the pipeHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultPipeHistShouldNotBeFound("maxDesignTemp.equals=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMaxDesignTempIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where maxDesignTemp in DEFAULT_MAX_DESIGN_TEMP or UPDATED_MAX_DESIGN_TEMP
        defaultPipeHistShouldBeFound("maxDesignTemp.in=" + DEFAULT_MAX_DESIGN_TEMP + "," + UPDATED_MAX_DESIGN_TEMP);

        // Get all the pipeHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultPipeHistShouldNotBeFound("maxDesignTemp.in=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByMaxDesignTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where maxDesignTemp is not null
        defaultPipeHistShouldBeFound("maxDesignTemp.specified=true");

        // Get all the pipeHistList where maxDesignTemp is null
        defaultPipeHistShouldNotBeFound("maxDesignTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatNumber equals to DEFAULT_HEAT_NUMBER
        defaultPipeHistShouldBeFound("heatNumber.equals=" + DEFAULT_HEAT_NUMBER);

        // Get all the pipeHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultPipeHistShouldNotBeFound("heatNumber.equals=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatNumberIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatNumber in DEFAULT_HEAT_NUMBER or UPDATED_HEAT_NUMBER
        defaultPipeHistShouldBeFound("heatNumber.in=" + DEFAULT_HEAT_NUMBER + "," + UPDATED_HEAT_NUMBER);

        // Get all the pipeHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultPipeHistShouldNotBeFound("heatNumber.in=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByHeatNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where heatNumber is not null
        defaultPipeHistShouldBeFound("heatNumber.specified=true");

        // Get all the pipeHistList where heatNumber is null
        defaultPipeHistShouldNotBeFound("heatNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where coord equals to DEFAULT_COORD
        defaultPipeHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the pipeHistList where coord equals to UPDATED_COORD
        defaultPipeHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultPipeHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the pipeHistList where coord equals to UPDATED_COORD
        defaultPipeHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where coord is not null
        defaultPipeHistShouldBeFound("coord.specified=true");

        // Get all the pipeHistList where coord is null
        defaultPipeHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designCoord equals to DEFAULT_DESIGN_COORD
        defaultPipeHistShouldBeFound("designCoord.equals=" + DEFAULT_DESIGN_COORD);

        // Get all the pipeHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultPipeHistShouldNotBeFound("designCoord.equals=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignCoordIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designCoord in DEFAULT_DESIGN_COORD or UPDATED_DESIGN_COORD
        defaultPipeHistShouldBeFound("designCoord.in=" + DEFAULT_DESIGN_COORD + "," + UPDATED_DESIGN_COORD);

        // Get all the pipeHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultPipeHistShouldNotBeFound("designCoord.in=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDesignCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where designCoord is not null
        defaultPipeHistShouldBeFound("designCoord.specified=true");

        // Get all the pipeHistList where designCoord is null
        defaultPipeHistShouldNotBeFound("designCoord.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpStart equals to DEFAULT_KP_START
        defaultPipeHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the pipeHistList where kpStart equals to UPDATED_KP_START
        defaultPipeHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultPipeHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the pipeHistList where kpStart equals to UPDATED_KP_START
        defaultPipeHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpStart is not null
        defaultPipeHistShouldBeFound("kpStart.specified=true");

        // Get all the pipeHistList where kpStart is null
        defaultPipeHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpEnd equals to DEFAULT_KP_END
        defaultPipeHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the pipeHistList where kpEnd equals to UPDATED_KP_END
        defaultPipeHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultPipeHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the pipeHistList where kpEnd equals to UPDATED_KP_END
        defaultPipeHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where kpEnd is not null
        defaultPipeHistShouldBeFound("kpEnd.specified=true");

        // Get all the pipeHistList where kpEnd is null
        defaultPipeHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipeHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipeHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultPipeHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the pipeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipeHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isCurrentFlag is not null
        defaultPipeHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the pipeHistList where isCurrentFlag is null
        defaultPipeHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipeHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipeHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipeHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipeHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipeHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipeHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllPipeHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where description equals to DEFAULT_DESCRIPTION
        defaultPipeHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the pipeHistList where description equals to UPDATED_DESCRIPTION
        defaultPipeHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultPipeHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the pipeHistList where description equals to UPDATED_DESCRIPTION
        defaultPipeHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where description is not null
        defaultPipeHistShouldBeFound("description.specified=true");

        // Get all the pipeHistList where description is null
        defaultPipeHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where comment equals to DEFAULT_COMMENT
        defaultPipeHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the pipeHistList where comment equals to UPDATED_COMMENT
        defaultPipeHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultPipeHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the pipeHistList where comment equals to UPDATED_COMMENT
        defaultPipeHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where comment is not null
        defaultPipeHistShouldBeFound("comment.specified=true");

        // Get all the pipeHistList where comment is null
        defaultPipeHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipeHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipeHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipeHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipeHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateCreate is not null
        defaultPipeHistShouldBeFound("dateCreate.specified=true");

        // Get all the pipeHistList where dateCreate is null
        defaultPipeHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipeHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipeHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipeHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipeHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where dateEdit is not null
        defaultPipeHistShouldBeFound("dateEdit.specified=true");

        // Get all the pipeHistList where dateEdit is null
        defaultPipeHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where creator equals to DEFAULT_CREATOR
        defaultPipeHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipeHistList where creator equals to UPDATED_CREATOR
        defaultPipeHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipeHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipeHistList where creator equals to UPDATED_CREATOR
        defaultPipeHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where creator is not null
        defaultPipeHistShouldBeFound("creator.specified=true");

        // Get all the pipeHistList where creator is null
        defaultPipeHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where editor equals to DEFAULT_EDITOR
        defaultPipeHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipeHistList where editor equals to UPDATED_EDITOR
        defaultPipeHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipeHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipeHistList where editor equals to UPDATED_EDITOR
        defaultPipeHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipeHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        // Get all the pipeHistList where editor is not null
        defaultPipeHistShouldBeFound("editor.specified=true");

        // Get all the pipeHistList where editor is null
        defaultPipeHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipeHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Pipe id = pipeHist.getId();
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idId = id.getId();

        // Get all the pipeHistList where id equals to idId
        defaultPipeHistShouldBeFound("idId.equals=" + idId);

        // Get all the pipeHistList where id equals to idId + 1
        defaultPipeHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = pipeHist.getIdPipelineSection();
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the pipeHistList where idPipelineSection equals to idPipelineSectionId
        defaultPipeHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the pipeHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultPipeHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdInternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListInternalCoating idInternalCoatType = ListInternalCoatingResourceIT.createEntity(em);
        em.persist(idInternalCoatType);
        em.flush();
        pipeHist.setIdInternalCoatType(idInternalCoatType);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idInternalCoatTypeId = idInternalCoatType.getId();

        // Get all the pipeHistList where idInternalCoatType equals to idInternalCoatTypeId
        defaultPipeHistShouldBeFound("idInternalCoatTypeId.equals=" + idInternalCoatTypeId);

        // Get all the pipeHistList where idInternalCoatType equals to idInternalCoatTypeId + 1
        defaultPipeHistShouldNotBeFound("idInternalCoatTypeId.equals=" + (idInternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListExternalCoating idExternalCoatType = ListExternalCoatingResourceIT.createEntity(em);
        em.persist(idExternalCoatType);
        em.flush();
        pipeHist.setIdExternalCoatType(idExternalCoatType);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the pipeHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultPipeHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the pipeHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultPipeHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdNominalWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        ListNominalWallThickness idNominalWallThickness = ListNominalWallThicknessResourceIT.createEntity(em);
        em.persist(idNominalWallThickness);
        em.flush();
        pipeHist.setIdNominalWallThickness(idNominalWallThickness);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idNominalWallThicknessId = idNominalWallThickness.getId();

        // Get all the pipeHistList where idNominalWallThickness equals to idNominalWallThicknessId
        defaultPipeHistShouldBeFound("idNominalWallThicknessId.equals=" + idNominalWallThicknessId);

        // Get all the pipeHistList where idNominalWallThickness equals to idNominalWallThicknessId + 1
        defaultPipeHistShouldNotBeFound("idNominalWallThicknessId.equals=" + (idNominalWallThicknessId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdPipeJointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint idPipeJoint = PipejointResourceIT.createEntity(em);
        em.persist(idPipeJoint);
        em.flush();
        pipeHist.setIdPipeJoint(idPipeJoint);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idPipeJointId = idPipeJoint.getId();

        // Get all the pipeHistList where idPipeJoint equals to idPipeJointId
        defaultPipeHistShouldBeFound("idPipeJointId.equals=" + idPipeJointId);

        // Get all the pipeHistList where idPipeJoint equals to idPipeJointId + 1
        defaultPipeHistShouldNotBeFound("idPipeJointId.equals=" + (idPipeJointId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdManufacturerIsEqualToSomething() throws Exception {
        // Initialize the database
        ListPipeManufacturer idManufacturer = ListPipeManufacturerResourceIT.createEntity(em);
        em.persist(idManufacturer);
        em.flush();
        pipeHist.setIdManufacturer(idManufacturer);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idManufacturerId = idManufacturer.getId();

        // Get all the pipeHistList where idManufacturer equals to idManufacturerId
        defaultPipeHistShouldBeFound("idManufacturerId.equals=" + idManufacturerId);

        // Get all the pipeHistList where idManufacturer equals to idManufacturerId + 1
        defaultPipeHistShouldNotBeFound("idManufacturerId.equals=" + (idManufacturerId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListPipeSpecification idSpecification = ListPipeSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        pipeHist.setIdSpecification(idSpecification);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the pipeHistList where idSpecification equals to idSpecificationId
        defaultPipeHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the pipeHistList where idSpecification equals to idSpecificationId + 1
        defaultPipeHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdLongSeamWeldTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListLongSeamWeldType idLongSeamWeldType = ListLongSeamWeldTypeResourceIT.createEntity(em);
        em.persist(idLongSeamWeldType);
        em.flush();
        pipeHist.setIdLongSeamWeldType(idLongSeamWeldType);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idLongSeamWeldTypeId = idLongSeamWeldType.getId();

        // Get all the pipeHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId
        defaultPipeHistShouldBeFound("idLongSeamWeldTypeId.equals=" + idLongSeamWeldTypeId);

        // Get all the pipeHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId + 1
        defaultPipeHistShouldNotBeFound("idLongSeamWeldTypeId.equals=" + (idLongSeamWeldTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdFabricationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListFabricationType idFabricationType = ListFabricationTypeResourceIT.createEntity(em);
        em.persist(idFabricationType);
        em.flush();
        pipeHist.setIdFabricationType(idFabricationType);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idFabricationTypeId = idFabricationType.getId();

        // Get all the pipeHistList where idFabricationType equals to idFabricationTypeId
        defaultPipeHistShouldBeFound("idFabricationTypeId.equals=" + idFabricationTypeId);

        // Get all the pipeHistList where idFabricationType equals to idFabricationTypeId + 1
        defaultPipeHistShouldNotBeFound("idFabricationTypeId.equals=" + (idFabricationTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        pipeHist.setIdMaterial(idMaterial);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the pipeHistList where idMaterial equals to idMaterialId
        defaultPipeHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the pipeHistList where idMaterial equals to idMaterialId + 1
        defaultPipeHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdMillLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMillLocation idMillLocation = ListMillLocationResourceIT.createEntity(em);
        em.persist(idMillLocation);
        em.flush();
        pipeHist.setIdMillLocation(idMillLocation);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idMillLocationId = idMillLocation.getId();

        // Get all the pipeHistList where idMillLocation equals to idMillLocationId
        defaultPipeHistShouldBeFound("idMillLocationId.equals=" + idMillLocationId);

        // Get all the pipeHistList where idMillLocation equals to idMillLocationId + 1
        defaultPipeHistShouldNotBeFound("idMillLocationId.equals=" + (idMillLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdSteelGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListSteelGrade idSteelGrade = ListSteelGradeResourceIT.createEntity(em);
        em.persist(idSteelGrade);
        em.flush();
        pipeHist.setIdSteelGrade(idSteelGrade);
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idSteelGradeId = idSteelGrade.getId();

        // Get all the pipeHistList where idSteelGrade equals to idSteelGradeId
        defaultPipeHistShouldBeFound("idSteelGradeId.equals=" + idSteelGradeId);

        // Get all the pipeHistList where idSteelGrade equals to idSteelGradeId + 1
        defaultPipeHistShouldNotBeFound("idSteelGradeId.equals=" + (idSteelGradeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipeHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = pipeHist.getIdStatus();
        pipeHistRepository.saveAndFlush(pipeHist);
        Long idStatusId = idStatus.getId();

        // Get all the pipeHistList where idStatus equals to idStatusId
        defaultPipeHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the pipeHistList where idStatus equals to idStatusId + 1
        defaultPipeHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipeHistShouldBeFound(String filter) throws Exception {
        restPipeHistMockMvc.perform(get("/api/pipe-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
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
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].burialDepth").value(hasItem(DEFAULT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].factBurialDepth").value(hasItem(DEFAULT_FACT_BURIAL_DEPTH)))
            .andExpect(jsonPath("$.[*].dateManufactured").value(hasItem(DEFAULT_DATE_MANUFACTURED.toString())))
            .andExpect(jsonPath("$.[*].millTestPressure").value(hasItem(DEFAULT_MILL_TEST_PRESSURE.intValue())))
            .andExpect(jsonPath("$.[*].designPressure").value(hasItem(DEFAULT_DESIGN_PRESSURE.intValue())))
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
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD)))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipeHistMockMvc.perform(get("/api/pipe-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipeHistShouldNotBeFound(String filter) throws Exception {
        restPipeHistMockMvc.perform(get("/api/pipe-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipeHistMockMvc.perform(get("/api/pipe-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipeHist() throws Exception {
        // Get the pipeHist
        restPipeHistMockMvc.perform(get("/api/pipe-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipeHist() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        int databaseSizeBeforeUpdate = pipeHistRepository.findAll().size();

        // Update the pipeHist
        PipeHist updatedPipeHist = pipeHistRepository.findById(pipeHist.getId()).get();
        // Disconnect from session so that the updates on updatedPipeHist are not directly saved in db
        em.detach(updatedPipeHist);
        updatedPipeHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .num(UPDATED_NUM)
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
            .isBurial(UPDATED_IS_BURIAL)
            .burialDepth(UPDATED_BURIAL_DEPTH)
            .factBurialDepth(UPDATED_FACT_BURIAL_DEPTH)
            .dateManufactured(UPDATED_DATE_MANUFACTURED)
            .millTestPressure(UPDATED_MILL_TEST_PRESSURE)
            .designPressure(UPDATED_DESIGN_PRESSURE)
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
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(updatedPipeHist);

        restPipeHistMockMvc.perform(put("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isOk());

        // Validate the PipeHist in the database
        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeUpdate);
        PipeHist testPipeHist = pipeHistList.get(pipeHistList.size() - 1);
        assertThat(testPipeHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testPipeHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testPipeHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testPipeHist.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testPipeHist.getDiameterOuterSteel()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL);
        assertThat(testPipeHist.getInternalCoatThickness()).isEqualTo(UPDATED_INTERNAL_COAT_THICKNESS);
        assertThat(testPipeHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testPipeHist.getIsConcrCoating()).isEqualTo(UPDATED_IS_CONCR_COATING);
        assertThat(testPipeHist.getConcrCoatThickness()).isEqualTo(UPDATED_CONCR_COAT_THICKNESS);
        assertThat(testPipeHist.getConcrCoatDensity()).isEqualTo(UPDATED_CONCR_COAT_DENSITY);
        assertThat(testPipeHist.getMeasWallThickness()).isEqualTo(UPDATED_MEAS_WALL_THICKNESS);
        assertThat(testPipeHist.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testPipeHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testPipeHist.getSmts()).isEqualTo(UPDATED_SMTS);
        assertThat(testPipeHist.getSmys()).isEqualTo(UPDATED_SMYS);
        assertThat(testPipeHist.getSdbm()).isEqualTo(UPDATED_SDBM);
        assertThat(testPipeHist.getSdaf()).isEqualTo(UPDATED_SDAF);
        assertThat(testPipeHist.getSdcs()).isEqualTo(UPDATED_SDCS);
        assertThat(testPipeHist.getAllowTensStrain()).isEqualTo(UPDATED_ALLOW_TENS_STRAIN);
        assertThat(testPipeHist.getCorrosionAllowance()).isEqualTo(UPDATED_CORROSION_ALLOWANCE);
        assertThat(testPipeHist.getFabricationAllowance()).isEqualTo(UPDATED_FABRICATION_ALLOWANCE);
        assertThat(testPipeHist.getIsBurial()).isEqualTo(UPDATED_IS_BURIAL);
        assertThat(testPipeHist.getBurialDepth()).isEqualTo(UPDATED_BURIAL_DEPTH);
        assertThat(testPipeHist.getFactBurialDepth()).isEqualTo(UPDATED_FACT_BURIAL_DEPTH);
        assertThat(testPipeHist.getDateManufactured()).isEqualTo(UPDATED_DATE_MANUFACTURED);
        assertThat(testPipeHist.getMillTestPressure()).isEqualTo(UPDATED_MILL_TEST_PRESSURE);
        assertThat(testPipeHist.getDesignPressure()).isEqualTo(UPDATED_DESIGN_PRESSURE);
        assertThat(testPipeHist.getIncidentalPressure()).isEqualTo(UPDATED_INCIDENTAL_PRESSURE);
        assertThat(testPipeHist.getDateInstalled()).isEqualTo(UPDATED_DATE_INSTALLED);
        assertThat(testPipeHist.getSeamOrient()).isEqualTo(UPDATED_SEAM_ORIENT);
        assertThat(testPipeHist.getSeamAngle()).isEqualTo(UPDATED_SEAM_ANGLE);
        assertThat(testPipeHist.getAzimuth()).isEqualTo(UPDATED_AZIMUTH);
        assertThat(testPipeHist.getSeabInstallTemp()).isEqualTo(UPDATED_SEAB_INSTALL_TEMP);
        assertThat(testPipeHist.getVerticalAngle()).isEqualTo(UPDATED_VERTICAL_ANGLE);
        assertThat(testPipeHist.getHeatTreatDurat()).isEqualTo(UPDATED_HEAT_TREAT_DURAT);
        assertThat(testPipeHist.getMaxDesignTemp()).isEqualTo(UPDATED_MAX_DESIGN_TEMP);
        assertThat(testPipeHist.getHeatNumber()).isEqualTo(UPDATED_HEAT_NUMBER);
        assertThat(testPipeHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testPipeHist.getDesignCoord()).isEqualTo(UPDATED_DESIGN_COORD);
        assertThat(testPipeHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testPipeHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testPipeHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testPipeHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPipeHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testPipeHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipeHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipeHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipeHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipeHist() throws Exception {
        int databaseSizeBeforeUpdate = pipeHistRepository.findAll().size();

        // Create the PipeHist
        PipeHistDTO pipeHistDTO = pipeHistMapper.toDto(pipeHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipeHistMockMvc.perform(put("/api/pipe-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipeHist in the database
        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipeHist() throws Exception {
        // Initialize the database
        pipeHistRepository.saveAndFlush(pipeHist);

        int databaseSizeBeforeDelete = pipeHistRepository.findAll().size();

        // Delete the pipeHist
        restPipeHistMockMvc.perform(delete("/api/pipe-hists/{id}", pipeHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PipeHist> pipeHistList = pipeHistRepository.findAll();
        assertThat(pipeHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipeHist.class);
        PipeHist pipeHist1 = new PipeHist();
        pipeHist1.setId(1L);
        PipeHist pipeHist2 = new PipeHist();
        pipeHist2.setId(pipeHist1.getId());
        assertThat(pipeHist1).isEqualTo(pipeHist2);
        pipeHist2.setId(2L);
        assertThat(pipeHist1).isNotEqualTo(pipeHist2);
        pipeHist1.setId(null);
        assertThat(pipeHist1).isNotEqualTo(pipeHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipeHistDTO.class);
        PipeHistDTO pipeHistDTO1 = new PipeHistDTO();
        pipeHistDTO1.setId(1L);
        PipeHistDTO pipeHistDTO2 = new PipeHistDTO();
        assertThat(pipeHistDTO1).isNotEqualTo(pipeHistDTO2);
        pipeHistDTO2.setId(pipeHistDTO1.getId());
        assertThat(pipeHistDTO1).isEqualTo(pipeHistDTO2);
        pipeHistDTO2.setId(2L);
        assertThat(pipeHistDTO1).isNotEqualTo(pipeHistDTO2);
        pipeHistDTO1.setId(null);
        assertThat(pipeHistDTO1).isNotEqualTo(pipeHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipeHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipeHistMapper.fromId(null)).isNull();
    }
}
