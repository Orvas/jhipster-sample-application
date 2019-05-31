package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.Bend;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListBendType;
import io.github.jhipster.application.domain.ListInternalCoating;
import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.ListBendManufacturer;
import io.github.jhipster.application.domain.ListBendSpecification;
import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.BendHistRepository;
import io.github.jhipster.application.service.BendHistService;
import io.github.jhipster.application.service.dto.BendHistDTO;
import io.github.jhipster.application.service.mapper.BendHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BendHistCriteria;
import io.github.jhipster.application.service.BendHistQueryService;

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
 * Integration tests for the {@Link BendHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BendHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final String DEFAULT_NUM = "AAAAAAAAAA";
    private static final String UPDATED_NUM = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_RADIUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_RADIUS = new BigDecimal(2);

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

    private static final BigDecimal DEFAULT_DESIGN_COORD = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_COORD = new BigDecimal(2);

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
    private BendHistRepository bendHistRepository;

    @Autowired
    private BendHistMapper bendHistMapper;

    @Autowired
    private BendHistService bendHistService;

    @Autowired
    private BendHistQueryService bendHistQueryService;

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

    private MockMvc restBendHistMockMvc;

    private BendHist bendHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BendHistResource bendHistResource = new BendHistResource(bendHistService, bendHistQueryService);
        this.restBendHistMockMvc = MockMvcBuilders.standaloneSetup(bendHistResource)
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
    public static BendHist createEntity(EntityManager em) {
        BendHist bendHist = new BendHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .num(DEFAULT_NUM)
            .radius(DEFAULT_RADIUS)
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
        Bend bend;
        if (TestUtil.findAll(em, Bend.class).isEmpty()) {
            bend = BendResourceIT.createEntity(em);
            em.persist(bend);
            em.flush();
        } else {
            bend = TestUtil.findAll(em, Bend.class).get(0);
        }
        bendHist.setId(bend);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        bendHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        bendHist.setIdStatus(listObjectStatus);
        return bendHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BendHist createUpdatedEntity(EntityManager em) {
        BendHist bendHist = new BendHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .num(UPDATED_NUM)
            .radius(UPDATED_RADIUS)
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
        Bend bend;
        if (TestUtil.findAll(em, Bend.class).isEmpty()) {
            bend = BendResourceIT.createUpdatedEntity(em);
            em.persist(bend);
            em.flush();
        } else {
            bend = TestUtil.findAll(em, Bend.class).get(0);
        }
        bendHist.setId(bend);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        bendHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        bendHist.setIdStatus(listObjectStatus);
        return bendHist;
    }

    @BeforeEach
    public void initTest() {
        bendHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createBendHist() throws Exception {
        int databaseSizeBeforeCreate = bendHistRepository.findAll().size();

        // Create the BendHist
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);
        restBendHistMockMvc.perform(post("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isCreated());

        // Validate the BendHist in the database
        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeCreate + 1);
        BendHist testBendHist = bendHistList.get(bendHistList.size() - 1);
        assertThat(testBendHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testBendHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testBendHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testBendHist.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testBendHist.getRadius()).isEqualTo(DEFAULT_RADIUS);
        assertThat(testBendHist.getDiameterOuterSteel()).isEqualTo(DEFAULT_DIAMETER_OUTER_STEEL);
        assertThat(testBendHist.getInternalCoatThickness()).isEqualTo(DEFAULT_INTERNAL_COAT_THICKNESS);
        assertThat(testBendHist.getExternalCoatThickness()).isEqualTo(DEFAULT_EXTERNAL_COAT_THICKNESS);
        assertThat(testBendHist.getIsConcrCoating()).isEqualTo(DEFAULT_IS_CONCR_COATING);
        assertThat(testBendHist.getConcrCoatThickness()).isEqualTo(DEFAULT_CONCR_COAT_THICKNESS);
        assertThat(testBendHist.getConcrCoatDensity()).isEqualTo(DEFAULT_CONCR_COAT_DENSITY);
        assertThat(testBendHist.getMeasWallThickness()).isEqualTo(DEFAULT_MEAS_WALL_THICKNESS);
        assertThat(testBendHist.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testBendHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testBendHist.getSmts()).isEqualTo(DEFAULT_SMTS);
        assertThat(testBendHist.getSmys()).isEqualTo(DEFAULT_SMYS);
        assertThat(testBendHist.getSdbm()).isEqualTo(DEFAULT_SDBM);
        assertThat(testBendHist.getSdaf()).isEqualTo(DEFAULT_SDAF);
        assertThat(testBendHist.getSdcs()).isEqualTo(DEFAULT_SDCS);
        assertThat(testBendHist.getAllowTensStrain()).isEqualTo(DEFAULT_ALLOW_TENS_STRAIN);
        assertThat(testBendHist.getCorrosionAllowance()).isEqualTo(DEFAULT_CORROSION_ALLOWANCE);
        assertThat(testBendHist.getFabricationAllowance()).isEqualTo(DEFAULT_FABRICATION_ALLOWANCE);
        assertThat(testBendHist.getIsBurial()).isEqualTo(DEFAULT_IS_BURIAL);
        assertThat(testBendHist.getBurialDepth()).isEqualTo(DEFAULT_BURIAL_DEPTH);
        assertThat(testBendHist.getFactBurialDepth()).isEqualTo(DEFAULT_FACT_BURIAL_DEPTH);
        assertThat(testBendHist.getDateManufactured()).isEqualTo(DEFAULT_DATE_MANUFACTURED);
        assertThat(testBendHist.getMillTestPressure()).isEqualTo(DEFAULT_MILL_TEST_PRESSURE);
        assertThat(testBendHist.getDesignPressure()).isEqualTo(DEFAULT_DESIGN_PRESSURE);
        assertThat(testBendHist.getIncidentalPressure()).isEqualTo(DEFAULT_INCIDENTAL_PRESSURE);
        assertThat(testBendHist.getDateInstalled()).isEqualTo(DEFAULT_DATE_INSTALLED);
        assertThat(testBendHist.getSeamOrient()).isEqualTo(DEFAULT_SEAM_ORIENT);
        assertThat(testBendHist.getSeamAngle()).isEqualTo(DEFAULT_SEAM_ANGLE);
        assertThat(testBendHist.getAzimuth()).isEqualTo(DEFAULT_AZIMUTH);
        assertThat(testBendHist.getSeabInstallTemp()).isEqualTo(DEFAULT_SEAB_INSTALL_TEMP);
        assertThat(testBendHist.getVerticalAngle()).isEqualTo(DEFAULT_VERTICAL_ANGLE);
        assertThat(testBendHist.getHeatTreatDurat()).isEqualTo(DEFAULT_HEAT_TREAT_DURAT);
        assertThat(testBendHist.getMaxDesignTemp()).isEqualTo(DEFAULT_MAX_DESIGN_TEMP);
        assertThat(testBendHist.getHeatNumber()).isEqualTo(DEFAULT_HEAT_NUMBER);
        assertThat(testBendHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testBendHist.getDesignCoord()).isEqualTo(DEFAULT_DESIGN_COORD);
        assertThat(testBendHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testBendHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testBendHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testBendHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBendHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testBendHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testBendHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testBendHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testBendHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createBendHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bendHistRepository.findAll().size();

        // Create the BendHist with an existing ID
        bendHist.setId(1L);
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBendHistMockMvc.perform(post("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BendHist in the database
        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = bendHistRepository.findAll().size();
        // set the field null
        bendHist.setDateFrom(null);

        // Create the BendHist, which fails.
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);

        restBendHistMockMvc.perform(post("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isBadRequest());

        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = bendHistRepository.findAll().size();
        // set the field null
        bendHist.setDateTo(null);

        // Create the BendHist, which fails.
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);

        restBendHistMockMvc.perform(post("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isBadRequest());

        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = bendHistRepository.findAll().size();
        // set the field null
        bendHist.setIsCurrentFlag(null);

        // Create the BendHist, which fails.
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);

        restBendHistMockMvc.perform(post("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isBadRequest());

        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBendHists() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList
        restBendHistMockMvc.perform(get("/api/bend-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bendHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM.toString())))
            .andExpect(jsonPath("$.[*].radius").value(hasItem(DEFAULT_RADIUS.intValue())))
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
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
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
    public void getBendHist() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get the bendHist
        restBendHistMockMvc.perform(get("/api/bend-hists/{id}", bendHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bendHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM.toString()))
            .andExpect(jsonPath("$.radius").value(DEFAULT_RADIUS.intValue()))
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
            .andExpect(jsonPath("$.designCoord").value(DEFAULT_DESIGN_COORD.intValue()))
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
    public void getAllBendHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultBendHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the bendHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultBendHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultBendHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the bendHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultBendHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateFrom is not null
        defaultBendHistShouldBeFound("dateFrom.specified=true");

        // Get all the bendHistList where dateFrom is null
        defaultBendHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultBendHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the bendHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultBendHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultBendHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the bendHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultBendHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllBendHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateTo equals to DEFAULT_DATE_TO
        defaultBendHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the bendHistList where dateTo equals to UPDATED_DATE_TO
        defaultBendHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultBendHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the bendHistList where dateTo equals to UPDATED_DATE_TO
        defaultBendHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateTo is not null
        defaultBendHistShouldBeFound("dateTo.specified=true");

        // Get all the bendHistList where dateTo is null
        defaultBendHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultBendHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the bendHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultBendHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultBendHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the bendHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultBendHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where idWrk equals to DEFAULT_ID_WRK
        defaultBendHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the bendHistList where idWrk equals to UPDATED_ID_WRK
        defaultBendHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultBendHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the bendHistList where idWrk equals to UPDATED_ID_WRK
        defaultBendHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where idWrk is not null
        defaultBendHistShouldBeFound("idWrk.specified=true");

        // Get all the bendHistList where idWrk is null
        defaultBendHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultBendHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the bendHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultBendHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultBendHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the bendHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultBendHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllBendHistsByNumIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where num equals to DEFAULT_NUM
        defaultBendHistShouldBeFound("num.equals=" + DEFAULT_NUM);

        // Get all the bendHistList where num equals to UPDATED_NUM
        defaultBendHistShouldNotBeFound("num.equals=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllBendHistsByNumIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where num in DEFAULT_NUM or UPDATED_NUM
        defaultBendHistShouldBeFound("num.in=" + DEFAULT_NUM + "," + UPDATED_NUM);

        // Get all the bendHistList where num equals to UPDATED_NUM
        defaultBendHistShouldNotBeFound("num.in=" + UPDATED_NUM);
    }

    @Test
    @Transactional
    public void getAllBendHistsByNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where num is not null
        defaultBendHistShouldBeFound("num.specified=true");

        // Get all the bendHistList where num is null
        defaultBendHistShouldNotBeFound("num.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByRadiusIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where radius equals to DEFAULT_RADIUS
        defaultBendHistShouldBeFound("radius.equals=" + DEFAULT_RADIUS);

        // Get all the bendHistList where radius equals to UPDATED_RADIUS
        defaultBendHistShouldNotBeFound("radius.equals=" + UPDATED_RADIUS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByRadiusIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where radius in DEFAULT_RADIUS or UPDATED_RADIUS
        defaultBendHistShouldBeFound("radius.in=" + DEFAULT_RADIUS + "," + UPDATED_RADIUS);

        // Get all the bendHistList where radius equals to UPDATED_RADIUS
        defaultBendHistShouldNotBeFound("radius.in=" + UPDATED_RADIUS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByRadiusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where radius is not null
        defaultBendHistShouldBeFound("radius.specified=true");

        // Get all the bendHistList where radius is null
        defaultBendHistShouldNotBeFound("radius.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDiameterOuterSteelIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where diameterOuterSteel equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBendHistShouldBeFound("diameterOuterSteel.equals=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the bendHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBendHistShouldNotBeFound("diameterOuterSteel.equals=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDiameterOuterSteelIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where diameterOuterSteel in DEFAULT_DIAMETER_OUTER_STEEL or UPDATED_DIAMETER_OUTER_STEEL
        defaultBendHistShouldBeFound("diameterOuterSteel.in=" + DEFAULT_DIAMETER_OUTER_STEEL + "," + UPDATED_DIAMETER_OUTER_STEEL);

        // Get all the bendHistList where diameterOuterSteel equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBendHistShouldNotBeFound("diameterOuterSteel.in=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDiameterOuterSteelIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where diameterOuterSteel is not null
        defaultBendHistShouldBeFound("diameterOuterSteel.specified=true");

        // Get all the bendHistList where diameterOuterSteel is null
        defaultBendHistShouldNotBeFound("diameterOuterSteel.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDiameterOuterSteelIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where diameterOuterSteel greater than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBendHistShouldBeFound("diameterOuterSteel.greaterOrEqualThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the bendHistList where diameterOuterSteel greater than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBendHistShouldNotBeFound("diameterOuterSteel.greaterOrEqualThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDiameterOuterSteelIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where diameterOuterSteel less than or equals to DEFAULT_DIAMETER_OUTER_STEEL
        defaultBendHistShouldNotBeFound("diameterOuterSteel.lessThan=" + DEFAULT_DIAMETER_OUTER_STEEL);

        // Get all the bendHistList where diameterOuterSteel less than or equals to UPDATED_DIAMETER_OUTER_STEEL
        defaultBendHistShouldBeFound("diameterOuterSteel.lessThan=" + UPDATED_DIAMETER_OUTER_STEEL);
    }


    @Test
    @Transactional
    public void getAllBendHistsByInternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where internalCoatThickness equals to DEFAULT_INTERNAL_COAT_THICKNESS
        defaultBendHistShouldBeFound("internalCoatThickness.equals=" + DEFAULT_INTERNAL_COAT_THICKNESS);

        // Get all the bendHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("internalCoatThickness.equals=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByInternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where internalCoatThickness in DEFAULT_INTERNAL_COAT_THICKNESS or UPDATED_INTERNAL_COAT_THICKNESS
        defaultBendHistShouldBeFound("internalCoatThickness.in=" + DEFAULT_INTERNAL_COAT_THICKNESS + "," + UPDATED_INTERNAL_COAT_THICKNESS);

        // Get all the bendHistList where internalCoatThickness equals to UPDATED_INTERNAL_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("internalCoatThickness.in=" + UPDATED_INTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByInternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where internalCoatThickness is not null
        defaultBendHistShouldBeFound("internalCoatThickness.specified=true");

        // Get all the bendHistList where internalCoatThickness is null
        defaultBendHistShouldNotBeFound("internalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByExternalCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where externalCoatThickness equals to DEFAULT_EXTERNAL_COAT_THICKNESS
        defaultBendHistShouldBeFound("externalCoatThickness.equals=" + DEFAULT_EXTERNAL_COAT_THICKNESS);

        // Get all the bendHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("externalCoatThickness.equals=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByExternalCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where externalCoatThickness in DEFAULT_EXTERNAL_COAT_THICKNESS or UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBendHistShouldBeFound("externalCoatThickness.in=" + DEFAULT_EXTERNAL_COAT_THICKNESS + "," + UPDATED_EXTERNAL_COAT_THICKNESS);

        // Get all the bendHistList where externalCoatThickness equals to UPDATED_EXTERNAL_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("externalCoatThickness.in=" + UPDATED_EXTERNAL_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByExternalCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where externalCoatThickness is not null
        defaultBendHistShouldBeFound("externalCoatThickness.specified=true");

        // Get all the bendHistList where externalCoatThickness is null
        defaultBendHistShouldNotBeFound("externalCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsConcrCoatingIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isConcrCoating equals to DEFAULT_IS_CONCR_COATING
        defaultBendHistShouldBeFound("isConcrCoating.equals=" + DEFAULT_IS_CONCR_COATING);

        // Get all the bendHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultBendHistShouldNotBeFound("isConcrCoating.equals=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsConcrCoatingIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isConcrCoating in DEFAULT_IS_CONCR_COATING or UPDATED_IS_CONCR_COATING
        defaultBendHistShouldBeFound("isConcrCoating.in=" + DEFAULT_IS_CONCR_COATING + "," + UPDATED_IS_CONCR_COATING);

        // Get all the bendHistList where isConcrCoating equals to UPDATED_IS_CONCR_COATING
        defaultBendHistShouldNotBeFound("isConcrCoating.in=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsConcrCoatingIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isConcrCoating is not null
        defaultBendHistShouldBeFound("isConcrCoating.specified=true");

        // Get all the bendHistList where isConcrCoating is null
        defaultBendHistShouldNotBeFound("isConcrCoating.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsConcrCoatingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isConcrCoating greater than or equals to DEFAULT_IS_CONCR_COATING
        defaultBendHistShouldBeFound("isConcrCoating.greaterOrEqualThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the bendHistList where isConcrCoating greater than or equals to UPDATED_IS_CONCR_COATING
        defaultBendHistShouldNotBeFound("isConcrCoating.greaterOrEqualThan=" + UPDATED_IS_CONCR_COATING);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsConcrCoatingIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isConcrCoating less than or equals to DEFAULT_IS_CONCR_COATING
        defaultBendHistShouldNotBeFound("isConcrCoating.lessThan=" + DEFAULT_IS_CONCR_COATING);

        // Get all the bendHistList where isConcrCoating less than or equals to UPDATED_IS_CONCR_COATING
        defaultBendHistShouldBeFound("isConcrCoating.lessThan=" + UPDATED_IS_CONCR_COATING);
    }


    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatThickness equals to DEFAULT_CONCR_COAT_THICKNESS
        defaultBendHistShouldBeFound("concrCoatThickness.equals=" + DEFAULT_CONCR_COAT_THICKNESS);

        // Get all the bendHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("concrCoatThickness.equals=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatThickness in DEFAULT_CONCR_COAT_THICKNESS or UPDATED_CONCR_COAT_THICKNESS
        defaultBendHistShouldBeFound("concrCoatThickness.in=" + DEFAULT_CONCR_COAT_THICKNESS + "," + UPDATED_CONCR_COAT_THICKNESS);

        // Get all the bendHistList where concrCoatThickness equals to UPDATED_CONCR_COAT_THICKNESS
        defaultBendHistShouldNotBeFound("concrCoatThickness.in=" + UPDATED_CONCR_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatThickness is not null
        defaultBendHistShouldBeFound("concrCoatThickness.specified=true");

        // Get all the bendHistList where concrCoatThickness is null
        defaultBendHistShouldNotBeFound("concrCoatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatDensity equals to DEFAULT_CONCR_COAT_DENSITY
        defaultBendHistShouldBeFound("concrCoatDensity.equals=" + DEFAULT_CONCR_COAT_DENSITY);

        // Get all the bendHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultBendHistShouldNotBeFound("concrCoatDensity.equals=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatDensityIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatDensity in DEFAULT_CONCR_COAT_DENSITY or UPDATED_CONCR_COAT_DENSITY
        defaultBendHistShouldBeFound("concrCoatDensity.in=" + DEFAULT_CONCR_COAT_DENSITY + "," + UPDATED_CONCR_COAT_DENSITY);

        // Get all the bendHistList where concrCoatDensity equals to UPDATED_CONCR_COAT_DENSITY
        defaultBendHistShouldNotBeFound("concrCoatDensity.in=" + UPDATED_CONCR_COAT_DENSITY);
    }

    @Test
    @Transactional
    public void getAllBendHistsByConcrCoatDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where concrCoatDensity is not null
        defaultBendHistShouldBeFound("concrCoatDensity.specified=true");

        // Get all the bendHistList where concrCoatDensity is null
        defaultBendHistShouldNotBeFound("concrCoatDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByMeasWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where measWallThickness equals to DEFAULT_MEAS_WALL_THICKNESS
        defaultBendHistShouldBeFound("measWallThickness.equals=" + DEFAULT_MEAS_WALL_THICKNESS);

        // Get all the bendHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultBendHistShouldNotBeFound("measWallThickness.equals=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMeasWallThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where measWallThickness in DEFAULT_MEAS_WALL_THICKNESS or UPDATED_MEAS_WALL_THICKNESS
        defaultBendHistShouldBeFound("measWallThickness.in=" + DEFAULT_MEAS_WALL_THICKNESS + "," + UPDATED_MEAS_WALL_THICKNESS);

        // Get all the bendHistList where measWallThickness equals to UPDATED_MEAS_WALL_THICKNESS
        defaultBendHistShouldNotBeFound("measWallThickness.in=" + UPDATED_MEAS_WALL_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMeasWallThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where measWallThickness is not null
        defaultBendHistShouldBeFound("measWallThickness.specified=true");

        // Get all the bendHistList where measWallThickness is null
        defaultBendHistShouldNotBeFound("measWallThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where length equals to DEFAULT_LENGTH
        defaultBendHistShouldBeFound("length.equals=" + DEFAULT_LENGTH);

        // Get all the bendHistList where length equals to UPDATED_LENGTH
        defaultBendHistShouldNotBeFound("length.equals=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByLengthIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where length in DEFAULT_LENGTH or UPDATED_LENGTH
        defaultBendHistShouldBeFound("length.in=" + DEFAULT_LENGTH + "," + UPDATED_LENGTH);

        // Get all the bendHistList where length equals to UPDATED_LENGTH
        defaultBendHistShouldNotBeFound("length.in=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where length is not null
        defaultBendHistShouldBeFound("length.specified=true");

        // Get all the bendHistList where length is null
        defaultBendHistShouldNotBeFound("length.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where length greater than or equals to DEFAULT_LENGTH
        defaultBendHistShouldBeFound("length.greaterOrEqualThan=" + DEFAULT_LENGTH);

        // Get all the bendHistList where length greater than or equals to UPDATED_LENGTH
        defaultBendHistShouldNotBeFound("length.greaterOrEqualThan=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where length less than or equals to DEFAULT_LENGTH
        defaultBendHistShouldNotBeFound("length.lessThan=" + DEFAULT_LENGTH);

        // Get all the bendHistList where length less than or equals to UPDATED_LENGTH
        defaultBendHistShouldBeFound("length.lessThan=" + UPDATED_LENGTH);
    }


    @Test
    @Transactional
    public void getAllBendHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where weight equals to DEFAULT_WEIGHT
        defaultBendHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the bendHistList where weight equals to UPDATED_WEIGHT
        defaultBendHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultBendHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the bendHistList where weight equals to UPDATED_WEIGHT
        defaultBendHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where weight is not null
        defaultBendHistShouldBeFound("weight.specified=true");

        // Get all the bendHistList where weight is null
        defaultBendHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmtsIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smts equals to DEFAULT_SMTS
        defaultBendHistShouldBeFound("smts.equals=" + DEFAULT_SMTS);

        // Get all the bendHistList where smts equals to UPDATED_SMTS
        defaultBendHistShouldNotBeFound("smts.equals=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmtsIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smts in DEFAULT_SMTS or UPDATED_SMTS
        defaultBendHistShouldBeFound("smts.in=" + DEFAULT_SMTS + "," + UPDATED_SMTS);

        // Get all the bendHistList where smts equals to UPDATED_SMTS
        defaultBendHistShouldNotBeFound("smts.in=" + UPDATED_SMTS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmtsIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smts is not null
        defaultBendHistShouldBeFound("smts.specified=true");

        // Get all the bendHistList where smts is null
        defaultBendHistShouldNotBeFound("smts.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmysIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smys equals to DEFAULT_SMYS
        defaultBendHistShouldBeFound("smys.equals=" + DEFAULT_SMYS);

        // Get all the bendHistList where smys equals to UPDATED_SMYS
        defaultBendHistShouldNotBeFound("smys.equals=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmysIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smys in DEFAULT_SMYS or UPDATED_SMYS
        defaultBendHistShouldBeFound("smys.in=" + DEFAULT_SMYS + "," + UPDATED_SMYS);

        // Get all the bendHistList where smys equals to UPDATED_SMYS
        defaultBendHistShouldNotBeFound("smys.in=" + UPDATED_SMYS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySmysIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where smys is not null
        defaultBendHistShouldBeFound("smys.specified=true");

        // Get all the bendHistList where smys is null
        defaultBendHistShouldNotBeFound("smys.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdbmIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdbm equals to DEFAULT_SDBM
        defaultBendHistShouldBeFound("sdbm.equals=" + DEFAULT_SDBM);

        // Get all the bendHistList where sdbm equals to UPDATED_SDBM
        defaultBendHistShouldNotBeFound("sdbm.equals=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdbmIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdbm in DEFAULT_SDBM or UPDATED_SDBM
        defaultBendHistShouldBeFound("sdbm.in=" + DEFAULT_SDBM + "," + UPDATED_SDBM);

        // Get all the bendHistList where sdbm equals to UPDATED_SDBM
        defaultBendHistShouldNotBeFound("sdbm.in=" + UPDATED_SDBM);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdbmIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdbm is not null
        defaultBendHistShouldBeFound("sdbm.specified=true");

        // Get all the bendHistList where sdbm is null
        defaultBendHistShouldNotBeFound("sdbm.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdafIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdaf equals to DEFAULT_SDAF
        defaultBendHistShouldBeFound("sdaf.equals=" + DEFAULT_SDAF);

        // Get all the bendHistList where sdaf equals to UPDATED_SDAF
        defaultBendHistShouldNotBeFound("sdaf.equals=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdafIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdaf in DEFAULT_SDAF or UPDATED_SDAF
        defaultBendHistShouldBeFound("sdaf.in=" + DEFAULT_SDAF + "," + UPDATED_SDAF);

        // Get all the bendHistList where sdaf equals to UPDATED_SDAF
        defaultBendHistShouldNotBeFound("sdaf.in=" + UPDATED_SDAF);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdafIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdaf is not null
        defaultBendHistShouldBeFound("sdaf.specified=true");

        // Get all the bendHistList where sdaf is null
        defaultBendHistShouldNotBeFound("sdaf.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdcsIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdcs equals to DEFAULT_SDCS
        defaultBendHistShouldBeFound("sdcs.equals=" + DEFAULT_SDCS);

        // Get all the bendHistList where sdcs equals to UPDATED_SDCS
        defaultBendHistShouldNotBeFound("sdcs.equals=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdcsIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdcs in DEFAULT_SDCS or UPDATED_SDCS
        defaultBendHistShouldBeFound("sdcs.in=" + DEFAULT_SDCS + "," + UPDATED_SDCS);

        // Get all the bendHistList where sdcs equals to UPDATED_SDCS
        defaultBendHistShouldNotBeFound("sdcs.in=" + UPDATED_SDCS);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySdcsIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where sdcs is not null
        defaultBendHistShouldBeFound("sdcs.specified=true");

        // Get all the bendHistList where sdcs is null
        defaultBendHistShouldNotBeFound("sdcs.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByAllowTensStrainIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where allowTensStrain equals to DEFAULT_ALLOW_TENS_STRAIN
        defaultBendHistShouldBeFound("allowTensStrain.equals=" + DEFAULT_ALLOW_TENS_STRAIN);

        // Get all the bendHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultBendHistShouldNotBeFound("allowTensStrain.equals=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllBendHistsByAllowTensStrainIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where allowTensStrain in DEFAULT_ALLOW_TENS_STRAIN or UPDATED_ALLOW_TENS_STRAIN
        defaultBendHistShouldBeFound("allowTensStrain.in=" + DEFAULT_ALLOW_TENS_STRAIN + "," + UPDATED_ALLOW_TENS_STRAIN);

        // Get all the bendHistList where allowTensStrain equals to UPDATED_ALLOW_TENS_STRAIN
        defaultBendHistShouldNotBeFound("allowTensStrain.in=" + UPDATED_ALLOW_TENS_STRAIN);
    }

    @Test
    @Transactional
    public void getAllBendHistsByAllowTensStrainIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where allowTensStrain is not null
        defaultBendHistShouldBeFound("allowTensStrain.specified=true");

        // Get all the bendHistList where allowTensStrain is null
        defaultBendHistShouldNotBeFound("allowTensStrain.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByCorrosionAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where corrosionAllowance equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBendHistShouldBeFound("corrosionAllowance.equals=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the bendHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultBendHistShouldNotBeFound("corrosionAllowance.equals=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCorrosionAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where corrosionAllowance in DEFAULT_CORROSION_ALLOWANCE or UPDATED_CORROSION_ALLOWANCE
        defaultBendHistShouldBeFound("corrosionAllowance.in=" + DEFAULT_CORROSION_ALLOWANCE + "," + UPDATED_CORROSION_ALLOWANCE);

        // Get all the bendHistList where corrosionAllowance equals to UPDATED_CORROSION_ALLOWANCE
        defaultBendHistShouldNotBeFound("corrosionAllowance.in=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCorrosionAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where corrosionAllowance is not null
        defaultBendHistShouldBeFound("corrosionAllowance.specified=true");

        // Get all the bendHistList where corrosionAllowance is null
        defaultBendHistShouldNotBeFound("corrosionAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByCorrosionAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where corrosionAllowance greater than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBendHistShouldBeFound("corrosionAllowance.greaterOrEqualThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the bendHistList where corrosionAllowance greater than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultBendHistShouldNotBeFound("corrosionAllowance.greaterOrEqualThan=" + UPDATED_CORROSION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCorrosionAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where corrosionAllowance less than or equals to DEFAULT_CORROSION_ALLOWANCE
        defaultBendHistShouldNotBeFound("corrosionAllowance.lessThan=" + DEFAULT_CORROSION_ALLOWANCE);

        // Get all the bendHistList where corrosionAllowance less than or equals to UPDATED_CORROSION_ALLOWANCE
        defaultBendHistShouldBeFound("corrosionAllowance.lessThan=" + UPDATED_CORROSION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllBendHistsByFabricationAllowanceIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where fabricationAllowance equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBendHistShouldBeFound("fabricationAllowance.equals=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the bendHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBendHistShouldNotBeFound("fabricationAllowance.equals=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFabricationAllowanceIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where fabricationAllowance in DEFAULT_FABRICATION_ALLOWANCE or UPDATED_FABRICATION_ALLOWANCE
        defaultBendHistShouldBeFound("fabricationAllowance.in=" + DEFAULT_FABRICATION_ALLOWANCE + "," + UPDATED_FABRICATION_ALLOWANCE);

        // Get all the bendHistList where fabricationAllowance equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBendHistShouldNotBeFound("fabricationAllowance.in=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFabricationAllowanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where fabricationAllowance is not null
        defaultBendHistShouldBeFound("fabricationAllowance.specified=true");

        // Get all the bendHistList where fabricationAllowance is null
        defaultBendHistShouldNotBeFound("fabricationAllowance.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByFabricationAllowanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where fabricationAllowance greater than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBendHistShouldBeFound("fabricationAllowance.greaterOrEqualThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the bendHistList where fabricationAllowance greater than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBendHistShouldNotBeFound("fabricationAllowance.greaterOrEqualThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFabricationAllowanceIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where fabricationAllowance less than or equals to DEFAULT_FABRICATION_ALLOWANCE
        defaultBendHistShouldNotBeFound("fabricationAllowance.lessThan=" + DEFAULT_FABRICATION_ALLOWANCE);

        // Get all the bendHistList where fabricationAllowance less than or equals to UPDATED_FABRICATION_ALLOWANCE
        defaultBendHistShouldBeFound("fabricationAllowance.lessThan=" + UPDATED_FABRICATION_ALLOWANCE);
    }


    @Test
    @Transactional
    public void getAllBendHistsByIsBurialIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isBurial equals to DEFAULT_IS_BURIAL
        defaultBendHistShouldBeFound("isBurial.equals=" + DEFAULT_IS_BURIAL);

        // Get all the bendHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultBendHistShouldNotBeFound("isBurial.equals=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsBurialIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isBurial in DEFAULT_IS_BURIAL or UPDATED_IS_BURIAL
        defaultBendHistShouldBeFound("isBurial.in=" + DEFAULT_IS_BURIAL + "," + UPDATED_IS_BURIAL);

        // Get all the bendHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultBendHistShouldNotBeFound("isBurial.in=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsBurialIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isBurial is not null
        defaultBendHistShouldBeFound("isBurial.specified=true");

        // Get all the bendHistList where isBurial is null
        defaultBendHistShouldNotBeFound("isBurial.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsBurialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isBurial greater than or equals to DEFAULT_IS_BURIAL
        defaultBendHistShouldBeFound("isBurial.greaterOrEqualThan=" + DEFAULT_IS_BURIAL);

        // Get all the bendHistList where isBurial greater than or equals to UPDATED_IS_BURIAL
        defaultBendHistShouldNotBeFound("isBurial.greaterOrEqualThan=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsBurialIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isBurial less than or equals to DEFAULT_IS_BURIAL
        defaultBendHistShouldNotBeFound("isBurial.lessThan=" + DEFAULT_IS_BURIAL);

        // Get all the bendHistList where isBurial less than or equals to UPDATED_IS_BURIAL
        defaultBendHistShouldBeFound("isBurial.lessThan=" + UPDATED_IS_BURIAL);
    }


    @Test
    @Transactional
    public void getAllBendHistsByBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where burialDepth equals to DEFAULT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("burialDepth.equals=" + DEFAULT_BURIAL_DEPTH);

        // Get all the bendHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("burialDepth.equals=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where burialDepth in DEFAULT_BURIAL_DEPTH or UPDATED_BURIAL_DEPTH
        defaultBendHistShouldBeFound("burialDepth.in=" + DEFAULT_BURIAL_DEPTH + "," + UPDATED_BURIAL_DEPTH);

        // Get all the bendHistList where burialDepth equals to UPDATED_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("burialDepth.in=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where burialDepth is not null
        defaultBendHistShouldBeFound("burialDepth.specified=true");

        // Get all the bendHistList where burialDepth is null
        defaultBendHistShouldNotBeFound("burialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where burialDepth greater than or equals to DEFAULT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("burialDepth.greaterOrEqualThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the bendHistList where burialDepth greater than or equals to UPDATED_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("burialDepth.greaterOrEqualThan=" + UPDATED_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where burialDepth less than or equals to DEFAULT_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("burialDepth.lessThan=" + DEFAULT_BURIAL_DEPTH);

        // Get all the bendHistList where burialDepth less than or equals to UPDATED_BURIAL_DEPTH
        defaultBendHistShouldBeFound("burialDepth.lessThan=" + UPDATED_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllBendHistsByFactBurialDepthIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where factBurialDepth equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("factBurialDepth.equals=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the bendHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("factBurialDepth.equals=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFactBurialDepthIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where factBurialDepth in DEFAULT_FACT_BURIAL_DEPTH or UPDATED_FACT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("factBurialDepth.in=" + DEFAULT_FACT_BURIAL_DEPTH + "," + UPDATED_FACT_BURIAL_DEPTH);

        // Get all the bendHistList where factBurialDepth equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("factBurialDepth.in=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFactBurialDepthIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where factBurialDepth is not null
        defaultBendHistShouldBeFound("factBurialDepth.specified=true");

        // Get all the bendHistList where factBurialDepth is null
        defaultBendHistShouldNotBeFound("factBurialDepth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByFactBurialDepthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where factBurialDepth greater than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("factBurialDepth.greaterOrEqualThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the bendHistList where factBurialDepth greater than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("factBurialDepth.greaterOrEqualThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByFactBurialDepthIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where factBurialDepth less than or equals to DEFAULT_FACT_BURIAL_DEPTH
        defaultBendHistShouldNotBeFound("factBurialDepth.lessThan=" + DEFAULT_FACT_BURIAL_DEPTH);

        // Get all the bendHistList where factBurialDepth less than or equals to UPDATED_FACT_BURIAL_DEPTH
        defaultBendHistShouldBeFound("factBurialDepth.lessThan=" + UPDATED_FACT_BURIAL_DEPTH);
    }


    @Test
    @Transactional
    public void getAllBendHistsByDateManufacturedIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateManufactured equals to DEFAULT_DATE_MANUFACTURED
        defaultBendHistShouldBeFound("dateManufactured.equals=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the bendHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultBendHistShouldNotBeFound("dateManufactured.equals=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateManufacturedIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateManufactured in DEFAULT_DATE_MANUFACTURED or UPDATED_DATE_MANUFACTURED
        defaultBendHistShouldBeFound("dateManufactured.in=" + DEFAULT_DATE_MANUFACTURED + "," + UPDATED_DATE_MANUFACTURED);

        // Get all the bendHistList where dateManufactured equals to UPDATED_DATE_MANUFACTURED
        defaultBendHistShouldNotBeFound("dateManufactured.in=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateManufacturedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateManufactured is not null
        defaultBendHistShouldBeFound("dateManufactured.specified=true");

        // Get all the bendHistList where dateManufactured is null
        defaultBendHistShouldNotBeFound("dateManufactured.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateManufacturedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateManufactured greater than or equals to DEFAULT_DATE_MANUFACTURED
        defaultBendHistShouldBeFound("dateManufactured.greaterOrEqualThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the bendHistList where dateManufactured greater than or equals to UPDATED_DATE_MANUFACTURED
        defaultBendHistShouldNotBeFound("dateManufactured.greaterOrEqualThan=" + UPDATED_DATE_MANUFACTURED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateManufacturedIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateManufactured less than or equals to DEFAULT_DATE_MANUFACTURED
        defaultBendHistShouldNotBeFound("dateManufactured.lessThan=" + DEFAULT_DATE_MANUFACTURED);

        // Get all the bendHistList where dateManufactured less than or equals to UPDATED_DATE_MANUFACTURED
        defaultBendHistShouldBeFound("dateManufactured.lessThan=" + UPDATED_DATE_MANUFACTURED);
    }


    @Test
    @Transactional
    public void getAllBendHistsByMillTestPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where millTestPressure equals to DEFAULT_MILL_TEST_PRESSURE
        defaultBendHistShouldBeFound("millTestPressure.equals=" + DEFAULT_MILL_TEST_PRESSURE);

        // Get all the bendHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultBendHistShouldNotBeFound("millTestPressure.equals=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMillTestPressureIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where millTestPressure in DEFAULT_MILL_TEST_PRESSURE or UPDATED_MILL_TEST_PRESSURE
        defaultBendHistShouldBeFound("millTestPressure.in=" + DEFAULT_MILL_TEST_PRESSURE + "," + UPDATED_MILL_TEST_PRESSURE);

        // Get all the bendHistList where millTestPressure equals to UPDATED_MILL_TEST_PRESSURE
        defaultBendHistShouldNotBeFound("millTestPressure.in=" + UPDATED_MILL_TEST_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMillTestPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where millTestPressure is not null
        defaultBendHistShouldBeFound("millTestPressure.specified=true");

        // Get all the bendHistList where millTestPressure is null
        defaultBendHistShouldNotBeFound("millTestPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designPressure equals to DEFAULT_DESIGN_PRESSURE
        defaultBendHistShouldBeFound("designPressure.equals=" + DEFAULT_DESIGN_PRESSURE);

        // Get all the bendHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultBendHistShouldNotBeFound("designPressure.equals=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignPressureIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designPressure in DEFAULT_DESIGN_PRESSURE or UPDATED_DESIGN_PRESSURE
        defaultBendHistShouldBeFound("designPressure.in=" + DEFAULT_DESIGN_PRESSURE + "," + UPDATED_DESIGN_PRESSURE);

        // Get all the bendHistList where designPressure equals to UPDATED_DESIGN_PRESSURE
        defaultBendHistShouldNotBeFound("designPressure.in=" + UPDATED_DESIGN_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designPressure is not null
        defaultBendHistShouldBeFound("designPressure.specified=true");

        // Get all the bendHistList where designPressure is null
        defaultBendHistShouldNotBeFound("designPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIncidentalPressureIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where incidentalPressure equals to DEFAULT_INCIDENTAL_PRESSURE
        defaultBendHistShouldBeFound("incidentalPressure.equals=" + DEFAULT_INCIDENTAL_PRESSURE);

        // Get all the bendHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultBendHistShouldNotBeFound("incidentalPressure.equals=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIncidentalPressureIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where incidentalPressure in DEFAULT_INCIDENTAL_PRESSURE or UPDATED_INCIDENTAL_PRESSURE
        defaultBendHistShouldBeFound("incidentalPressure.in=" + DEFAULT_INCIDENTAL_PRESSURE + "," + UPDATED_INCIDENTAL_PRESSURE);

        // Get all the bendHistList where incidentalPressure equals to UPDATED_INCIDENTAL_PRESSURE
        defaultBendHistShouldNotBeFound("incidentalPressure.in=" + UPDATED_INCIDENTAL_PRESSURE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIncidentalPressureIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where incidentalPressure is not null
        defaultBendHistShouldBeFound("incidentalPressure.specified=true");

        // Get all the bendHistList where incidentalPressure is null
        defaultBendHistShouldNotBeFound("incidentalPressure.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateInstalledIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateInstalled equals to DEFAULT_DATE_INSTALLED
        defaultBendHistShouldBeFound("dateInstalled.equals=" + DEFAULT_DATE_INSTALLED);

        // Get all the bendHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultBendHistShouldNotBeFound("dateInstalled.equals=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateInstalledIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateInstalled in DEFAULT_DATE_INSTALLED or UPDATED_DATE_INSTALLED
        defaultBendHistShouldBeFound("dateInstalled.in=" + DEFAULT_DATE_INSTALLED + "," + UPDATED_DATE_INSTALLED);

        // Get all the bendHistList where dateInstalled equals to UPDATED_DATE_INSTALLED
        defaultBendHistShouldNotBeFound("dateInstalled.in=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateInstalledIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateInstalled is not null
        defaultBendHistShouldBeFound("dateInstalled.specified=true");

        // Get all the bendHistList where dateInstalled is null
        defaultBendHistShouldNotBeFound("dateInstalled.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateInstalledIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateInstalled greater than or equals to DEFAULT_DATE_INSTALLED
        defaultBendHistShouldBeFound("dateInstalled.greaterOrEqualThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the bendHistList where dateInstalled greater than or equals to UPDATED_DATE_INSTALLED
        defaultBendHistShouldNotBeFound("dateInstalled.greaterOrEqualThan=" + UPDATED_DATE_INSTALLED);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateInstalledIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateInstalled less than or equals to DEFAULT_DATE_INSTALLED
        defaultBendHistShouldNotBeFound("dateInstalled.lessThan=" + DEFAULT_DATE_INSTALLED);

        // Get all the bendHistList where dateInstalled less than or equals to UPDATED_DATE_INSTALLED
        defaultBendHistShouldBeFound("dateInstalled.lessThan=" + UPDATED_DATE_INSTALLED);
    }


    @Test
    @Transactional
    public void getAllBendHistsBySeamOrientIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamOrient equals to DEFAULT_SEAM_ORIENT
        defaultBendHistShouldBeFound("seamOrient.equals=" + DEFAULT_SEAM_ORIENT);

        // Get all the bendHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultBendHistShouldNotBeFound("seamOrient.equals=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeamOrientIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamOrient in DEFAULT_SEAM_ORIENT or UPDATED_SEAM_ORIENT
        defaultBendHistShouldBeFound("seamOrient.in=" + DEFAULT_SEAM_ORIENT + "," + UPDATED_SEAM_ORIENT);

        // Get all the bendHistList where seamOrient equals to UPDATED_SEAM_ORIENT
        defaultBendHistShouldNotBeFound("seamOrient.in=" + UPDATED_SEAM_ORIENT);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeamOrientIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamOrient is not null
        defaultBendHistShouldBeFound("seamOrient.specified=true");

        // Get all the bendHistList where seamOrient is null
        defaultBendHistShouldNotBeFound("seamOrient.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeamAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamAngle equals to DEFAULT_SEAM_ANGLE
        defaultBendHistShouldBeFound("seamAngle.equals=" + DEFAULT_SEAM_ANGLE);

        // Get all the bendHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultBendHistShouldNotBeFound("seamAngle.equals=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeamAngleIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamAngle in DEFAULT_SEAM_ANGLE or UPDATED_SEAM_ANGLE
        defaultBendHistShouldBeFound("seamAngle.in=" + DEFAULT_SEAM_ANGLE + "," + UPDATED_SEAM_ANGLE);

        // Get all the bendHistList where seamAngle equals to UPDATED_SEAM_ANGLE
        defaultBendHistShouldNotBeFound("seamAngle.in=" + UPDATED_SEAM_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeamAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seamAngle is not null
        defaultBendHistShouldBeFound("seamAngle.specified=true");

        // Get all the bendHistList where seamAngle is null
        defaultBendHistShouldNotBeFound("seamAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByAzimuthIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where azimuth equals to DEFAULT_AZIMUTH
        defaultBendHistShouldBeFound("azimuth.equals=" + DEFAULT_AZIMUTH);

        // Get all the bendHistList where azimuth equals to UPDATED_AZIMUTH
        defaultBendHistShouldNotBeFound("azimuth.equals=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByAzimuthIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where azimuth in DEFAULT_AZIMUTH or UPDATED_AZIMUTH
        defaultBendHistShouldBeFound("azimuth.in=" + DEFAULT_AZIMUTH + "," + UPDATED_AZIMUTH);

        // Get all the bendHistList where azimuth equals to UPDATED_AZIMUTH
        defaultBendHistShouldNotBeFound("azimuth.in=" + UPDATED_AZIMUTH);
    }

    @Test
    @Transactional
    public void getAllBendHistsByAzimuthIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where azimuth is not null
        defaultBendHistShouldBeFound("azimuth.specified=true");

        // Get all the bendHistList where azimuth is null
        defaultBendHistShouldNotBeFound("azimuth.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeabInstallTempIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seabInstallTemp equals to DEFAULT_SEAB_INSTALL_TEMP
        defaultBendHistShouldBeFound("seabInstallTemp.equals=" + DEFAULT_SEAB_INSTALL_TEMP);

        // Get all the bendHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultBendHistShouldNotBeFound("seabInstallTemp.equals=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeabInstallTempIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seabInstallTemp in DEFAULT_SEAB_INSTALL_TEMP or UPDATED_SEAB_INSTALL_TEMP
        defaultBendHistShouldBeFound("seabInstallTemp.in=" + DEFAULT_SEAB_INSTALL_TEMP + "," + UPDATED_SEAB_INSTALL_TEMP);

        // Get all the bendHistList where seabInstallTemp equals to UPDATED_SEAB_INSTALL_TEMP
        defaultBendHistShouldNotBeFound("seabInstallTemp.in=" + UPDATED_SEAB_INSTALL_TEMP);
    }

    @Test
    @Transactional
    public void getAllBendHistsBySeabInstallTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where seabInstallTemp is not null
        defaultBendHistShouldBeFound("seabInstallTemp.specified=true");

        // Get all the bendHistList where seabInstallTemp is null
        defaultBendHistShouldNotBeFound("seabInstallTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByVerticalAngleIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where verticalAngle equals to DEFAULT_VERTICAL_ANGLE
        defaultBendHistShouldBeFound("verticalAngle.equals=" + DEFAULT_VERTICAL_ANGLE);

        // Get all the bendHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultBendHistShouldNotBeFound("verticalAngle.equals=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByVerticalAngleIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where verticalAngle in DEFAULT_VERTICAL_ANGLE or UPDATED_VERTICAL_ANGLE
        defaultBendHistShouldBeFound("verticalAngle.in=" + DEFAULT_VERTICAL_ANGLE + "," + UPDATED_VERTICAL_ANGLE);

        // Get all the bendHistList where verticalAngle equals to UPDATED_VERTICAL_ANGLE
        defaultBendHistShouldNotBeFound("verticalAngle.in=" + UPDATED_VERTICAL_ANGLE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByVerticalAngleIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where verticalAngle is not null
        defaultBendHistShouldBeFound("verticalAngle.specified=true");

        // Get all the bendHistList where verticalAngle is null
        defaultBendHistShouldNotBeFound("verticalAngle.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatTreatDuratIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatTreatDurat equals to DEFAULT_HEAT_TREAT_DURAT
        defaultBendHistShouldBeFound("heatTreatDurat.equals=" + DEFAULT_HEAT_TREAT_DURAT);

        // Get all the bendHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultBendHistShouldNotBeFound("heatTreatDurat.equals=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatTreatDuratIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatTreatDurat in DEFAULT_HEAT_TREAT_DURAT or UPDATED_HEAT_TREAT_DURAT
        defaultBendHistShouldBeFound("heatTreatDurat.in=" + DEFAULT_HEAT_TREAT_DURAT + "," + UPDATED_HEAT_TREAT_DURAT);

        // Get all the bendHistList where heatTreatDurat equals to UPDATED_HEAT_TREAT_DURAT
        defaultBendHistShouldNotBeFound("heatTreatDurat.in=" + UPDATED_HEAT_TREAT_DURAT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatTreatDuratIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatTreatDurat is not null
        defaultBendHistShouldBeFound("heatTreatDurat.specified=true");

        // Get all the bendHistList where heatTreatDurat is null
        defaultBendHistShouldNotBeFound("heatTreatDurat.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByMaxDesignTempIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where maxDesignTemp equals to DEFAULT_MAX_DESIGN_TEMP
        defaultBendHistShouldBeFound("maxDesignTemp.equals=" + DEFAULT_MAX_DESIGN_TEMP);

        // Get all the bendHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultBendHistShouldNotBeFound("maxDesignTemp.equals=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMaxDesignTempIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where maxDesignTemp in DEFAULT_MAX_DESIGN_TEMP or UPDATED_MAX_DESIGN_TEMP
        defaultBendHistShouldBeFound("maxDesignTemp.in=" + DEFAULT_MAX_DESIGN_TEMP + "," + UPDATED_MAX_DESIGN_TEMP);

        // Get all the bendHistList where maxDesignTemp equals to UPDATED_MAX_DESIGN_TEMP
        defaultBendHistShouldNotBeFound("maxDesignTemp.in=" + UPDATED_MAX_DESIGN_TEMP);
    }

    @Test
    @Transactional
    public void getAllBendHistsByMaxDesignTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where maxDesignTemp is not null
        defaultBendHistShouldBeFound("maxDesignTemp.specified=true");

        // Get all the bendHistList where maxDesignTemp is null
        defaultBendHistShouldNotBeFound("maxDesignTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatNumber equals to DEFAULT_HEAT_NUMBER
        defaultBendHistShouldBeFound("heatNumber.equals=" + DEFAULT_HEAT_NUMBER);

        // Get all the bendHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultBendHistShouldNotBeFound("heatNumber.equals=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatNumberIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatNumber in DEFAULT_HEAT_NUMBER or UPDATED_HEAT_NUMBER
        defaultBendHistShouldBeFound("heatNumber.in=" + DEFAULT_HEAT_NUMBER + "," + UPDATED_HEAT_NUMBER);

        // Get all the bendHistList where heatNumber equals to UPDATED_HEAT_NUMBER
        defaultBendHistShouldNotBeFound("heatNumber.in=" + UPDATED_HEAT_NUMBER);
    }

    @Test
    @Transactional
    public void getAllBendHistsByHeatNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where heatNumber is not null
        defaultBendHistShouldBeFound("heatNumber.specified=true");

        // Get all the bendHistList where heatNumber is null
        defaultBendHistShouldNotBeFound("heatNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where coord equals to DEFAULT_COORD
        defaultBendHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the bendHistList where coord equals to UPDATED_COORD
        defaultBendHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultBendHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the bendHistList where coord equals to UPDATED_COORD
        defaultBendHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where coord is not null
        defaultBendHistShouldBeFound("coord.specified=true");

        // Get all the bendHistList where coord is null
        defaultBendHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designCoord equals to DEFAULT_DESIGN_COORD
        defaultBendHistShouldBeFound("designCoord.equals=" + DEFAULT_DESIGN_COORD);

        // Get all the bendHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultBendHistShouldNotBeFound("designCoord.equals=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignCoordIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designCoord in DEFAULT_DESIGN_COORD or UPDATED_DESIGN_COORD
        defaultBendHistShouldBeFound("designCoord.in=" + DEFAULT_DESIGN_COORD + "," + UPDATED_DESIGN_COORD);

        // Get all the bendHistList where designCoord equals to UPDATED_DESIGN_COORD
        defaultBendHistShouldNotBeFound("designCoord.in=" + UPDATED_DESIGN_COORD);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDesignCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where designCoord is not null
        defaultBendHistShouldBeFound("designCoord.specified=true");

        // Get all the bendHistList where designCoord is null
        defaultBendHistShouldNotBeFound("designCoord.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpStart equals to DEFAULT_KP_START
        defaultBendHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the bendHistList where kpStart equals to UPDATED_KP_START
        defaultBendHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultBendHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the bendHistList where kpStart equals to UPDATED_KP_START
        defaultBendHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpStart is not null
        defaultBendHistShouldBeFound("kpStart.specified=true");

        // Get all the bendHistList where kpStart is null
        defaultBendHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpEnd equals to DEFAULT_KP_END
        defaultBendHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the bendHistList where kpEnd equals to UPDATED_KP_END
        defaultBendHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultBendHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the bendHistList where kpEnd equals to UPDATED_KP_END
        defaultBendHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllBendHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where kpEnd is not null
        defaultBendHistShouldBeFound("kpEnd.specified=true");

        // Get all the bendHistList where kpEnd is null
        defaultBendHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultBendHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the bendHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultBendHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultBendHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the bendHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultBendHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isCurrentFlag is not null
        defaultBendHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the bendHistList where isCurrentFlag is null
        defaultBendHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultBendHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the bendHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultBendHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllBendHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultBendHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the bendHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultBendHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllBendHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where description equals to DEFAULT_DESCRIPTION
        defaultBendHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the bendHistList where description equals to UPDATED_DESCRIPTION
        defaultBendHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultBendHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the bendHistList where description equals to UPDATED_DESCRIPTION
        defaultBendHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where description is not null
        defaultBendHistShouldBeFound("description.specified=true");

        // Get all the bendHistList where description is null
        defaultBendHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where comment equals to DEFAULT_COMMENT
        defaultBendHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the bendHistList where comment equals to UPDATED_COMMENT
        defaultBendHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultBendHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the bendHistList where comment equals to UPDATED_COMMENT
        defaultBendHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where comment is not null
        defaultBendHistShouldBeFound("comment.specified=true");

        // Get all the bendHistList where comment is null
        defaultBendHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultBendHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the bendHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBendHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultBendHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the bendHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBendHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateCreate is not null
        defaultBendHistShouldBeFound("dateCreate.specified=true");

        // Get all the bendHistList where dateCreate is null
        defaultBendHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultBendHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the bendHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBendHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultBendHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the bendHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBendHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBendHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where dateEdit is not null
        defaultBendHistShouldBeFound("dateEdit.specified=true");

        // Get all the bendHistList where dateEdit is null
        defaultBendHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where creator equals to DEFAULT_CREATOR
        defaultBendHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the bendHistList where creator equals to UPDATED_CREATOR
        defaultBendHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultBendHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the bendHistList where creator equals to UPDATED_CREATOR
        defaultBendHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBendHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where creator is not null
        defaultBendHistShouldBeFound("creator.specified=true");

        // Get all the bendHistList where creator is null
        defaultBendHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where editor equals to DEFAULT_EDITOR
        defaultBendHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the bendHistList where editor equals to UPDATED_EDITOR
        defaultBendHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBendHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultBendHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the bendHistList where editor equals to UPDATED_EDITOR
        defaultBendHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBendHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        // Get all the bendHistList where editor is not null
        defaultBendHistShouldBeFound("editor.specified=true");

        // Get all the bendHistList where editor is null
        defaultBendHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllBendHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Bend id = bendHist.getId();
        bendHistRepository.saveAndFlush(bendHist);
        Long idId = id.getId();

        // Get all the bendHistList where id equals to idId
        defaultBendHistShouldBeFound("idId.equals=" + idId);

        // Get all the bendHistList where id equals to idId + 1
        defaultBendHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = bendHist.getIdPipelineSection();
        bendHistRepository.saveAndFlush(bendHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the bendHistList where idPipelineSection equals to idPipelineSectionId
        defaultBendHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the bendHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultBendHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListBendType idType = ListBendTypeResourceIT.createEntity(em);
        em.persist(idType);
        em.flush();
        bendHist.setIdType(idType);
        bendHistRepository.saveAndFlush(bendHist);
        Long idTypeId = idType.getId();

        // Get all the bendHistList where idType equals to idTypeId
        defaultBendHistShouldBeFound("idTypeId.equals=" + idTypeId);

        // Get all the bendHistList where idType equals to idTypeId + 1
        defaultBendHistShouldNotBeFound("idTypeId.equals=" + (idTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdInternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListInternalCoating idInternalCoatType = ListInternalCoatingResourceIT.createEntity(em);
        em.persist(idInternalCoatType);
        em.flush();
        bendHist.setIdInternalCoatType(idInternalCoatType);
        bendHistRepository.saveAndFlush(bendHist);
        Long idInternalCoatTypeId = idInternalCoatType.getId();

        // Get all the bendHistList where idInternalCoatType equals to idInternalCoatTypeId
        defaultBendHistShouldBeFound("idInternalCoatTypeId.equals=" + idInternalCoatTypeId);

        // Get all the bendHistList where idInternalCoatType equals to idInternalCoatTypeId + 1
        defaultBendHistShouldNotBeFound("idInternalCoatTypeId.equals=" + (idInternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdExternalCoatTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListExternalCoating idExternalCoatType = ListExternalCoatingResourceIT.createEntity(em);
        em.persist(idExternalCoatType);
        em.flush();
        bendHist.setIdExternalCoatType(idExternalCoatType);
        bendHistRepository.saveAndFlush(bendHist);
        Long idExternalCoatTypeId = idExternalCoatType.getId();

        // Get all the bendHistList where idExternalCoatType equals to idExternalCoatTypeId
        defaultBendHistShouldBeFound("idExternalCoatTypeId.equals=" + idExternalCoatTypeId);

        // Get all the bendHistList where idExternalCoatType equals to idExternalCoatTypeId + 1
        defaultBendHistShouldNotBeFound("idExternalCoatTypeId.equals=" + (idExternalCoatTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdNominalWallThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        ListNominalWallThickness idNominalWallThickness = ListNominalWallThicknessResourceIT.createEntity(em);
        em.persist(idNominalWallThickness);
        em.flush();
        bendHist.setIdNominalWallThickness(idNominalWallThickness);
        bendHistRepository.saveAndFlush(bendHist);
        Long idNominalWallThicknessId = idNominalWallThickness.getId();

        // Get all the bendHistList where idNominalWallThickness equals to idNominalWallThicknessId
        defaultBendHistShouldBeFound("idNominalWallThicknessId.equals=" + idNominalWallThicknessId);

        // Get all the bendHistList where idNominalWallThickness equals to idNominalWallThicknessId + 1
        defaultBendHistShouldNotBeFound("idNominalWallThicknessId.equals=" + (idNominalWallThicknessId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdPipeJointIsEqualToSomething() throws Exception {
        // Initialize the database
        Pipejoint idPipeJoint = PipejointResourceIT.createEntity(em);
        em.persist(idPipeJoint);
        em.flush();
        bendHist.setIdPipeJoint(idPipeJoint);
        bendHistRepository.saveAndFlush(bendHist);
        Long idPipeJointId = idPipeJoint.getId();

        // Get all the bendHistList where idPipeJoint equals to idPipeJointId
        defaultBendHistShouldBeFound("idPipeJointId.equals=" + idPipeJointId);

        // Get all the bendHistList where idPipeJoint equals to idPipeJointId + 1
        defaultBendHistShouldNotBeFound("idPipeJointId.equals=" + (idPipeJointId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdManufacturerIsEqualToSomething() throws Exception {
        // Initialize the database
        ListBendManufacturer idManufacturer = ListBendManufacturerResourceIT.createEntity(em);
        em.persist(idManufacturer);
        em.flush();
        bendHist.setIdManufacturer(idManufacturer);
        bendHistRepository.saveAndFlush(bendHist);
        Long idManufacturerId = idManufacturer.getId();

        // Get all the bendHistList where idManufacturer equals to idManufacturerId
        defaultBendHistShouldBeFound("idManufacturerId.equals=" + idManufacturerId);

        // Get all the bendHistList where idManufacturer equals to idManufacturerId + 1
        defaultBendHistShouldNotBeFound("idManufacturerId.equals=" + (idManufacturerId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdSpecificationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListBendSpecification idSpecification = ListBendSpecificationResourceIT.createEntity(em);
        em.persist(idSpecification);
        em.flush();
        bendHist.setIdSpecification(idSpecification);
        bendHistRepository.saveAndFlush(bendHist);
        Long idSpecificationId = idSpecification.getId();

        // Get all the bendHistList where idSpecification equals to idSpecificationId
        defaultBendHistShouldBeFound("idSpecificationId.equals=" + idSpecificationId);

        // Get all the bendHistList where idSpecification equals to idSpecificationId + 1
        defaultBendHistShouldNotBeFound("idSpecificationId.equals=" + (idSpecificationId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdLongSeamWeldTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListLongSeamWeldType idLongSeamWeldType = ListLongSeamWeldTypeResourceIT.createEntity(em);
        em.persist(idLongSeamWeldType);
        em.flush();
        bendHist.setIdLongSeamWeldType(idLongSeamWeldType);
        bendHistRepository.saveAndFlush(bendHist);
        Long idLongSeamWeldTypeId = idLongSeamWeldType.getId();

        // Get all the bendHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId
        defaultBendHistShouldBeFound("idLongSeamWeldTypeId.equals=" + idLongSeamWeldTypeId);

        // Get all the bendHistList where idLongSeamWeldType equals to idLongSeamWeldTypeId + 1
        defaultBendHistShouldNotBeFound("idLongSeamWeldTypeId.equals=" + (idLongSeamWeldTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdFabricationTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListFabricationType idFabricationType = ListFabricationTypeResourceIT.createEntity(em);
        em.persist(idFabricationType);
        em.flush();
        bendHist.setIdFabricationType(idFabricationType);
        bendHistRepository.saveAndFlush(bendHist);
        Long idFabricationTypeId = idFabricationType.getId();

        // Get all the bendHistList where idFabricationType equals to idFabricationTypeId
        defaultBendHistShouldBeFound("idFabricationTypeId.equals=" + idFabricationTypeId);

        // Get all the bendHistList where idFabricationType equals to idFabricationTypeId + 1
        defaultBendHistShouldNotBeFound("idFabricationTypeId.equals=" + (idFabricationTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        bendHist.setIdMaterial(idMaterial);
        bendHistRepository.saveAndFlush(bendHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the bendHistList where idMaterial equals to idMaterialId
        defaultBendHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the bendHistList where idMaterial equals to idMaterialId + 1
        defaultBendHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdMillLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMillLocation idMillLocation = ListMillLocationResourceIT.createEntity(em);
        em.persist(idMillLocation);
        em.flush();
        bendHist.setIdMillLocation(idMillLocation);
        bendHistRepository.saveAndFlush(bendHist);
        Long idMillLocationId = idMillLocation.getId();

        // Get all the bendHistList where idMillLocation equals to idMillLocationId
        defaultBendHistShouldBeFound("idMillLocationId.equals=" + idMillLocationId);

        // Get all the bendHistList where idMillLocation equals to idMillLocationId + 1
        defaultBendHistShouldNotBeFound("idMillLocationId.equals=" + (idMillLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdSteelGradeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListSteelGrade idSteelGrade = ListSteelGradeResourceIT.createEntity(em);
        em.persist(idSteelGrade);
        em.flush();
        bendHist.setIdSteelGrade(idSteelGrade);
        bendHistRepository.saveAndFlush(bendHist);
        Long idSteelGradeId = idSteelGrade.getId();

        // Get all the bendHistList where idSteelGrade equals to idSteelGradeId
        defaultBendHistShouldBeFound("idSteelGradeId.equals=" + idSteelGradeId);

        // Get all the bendHistList where idSteelGrade equals to idSteelGradeId + 1
        defaultBendHistShouldNotBeFound("idSteelGradeId.equals=" + (idSteelGradeId + 1));
    }


    @Test
    @Transactional
    public void getAllBendHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = bendHist.getIdStatus();
        bendHistRepository.saveAndFlush(bendHist);
        Long idStatusId = idStatus.getId();

        // Get all the bendHistList where idStatus equals to idStatusId
        defaultBendHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the bendHistList where idStatus equals to idStatusId + 1
        defaultBendHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBendHistShouldBeFound(String filter) throws Exception {
        restBendHistMockMvc.perform(get("/api/bend-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bendHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].radius").value(hasItem(DEFAULT_RADIUS.intValue())))
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
            .andExpect(jsonPath("$.[*].designCoord").value(hasItem(DEFAULT_DESIGN_COORD.intValue())))
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
        restBendHistMockMvc.perform(get("/api/bend-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBendHistShouldNotBeFound(String filter) throws Exception {
        restBendHistMockMvc.perform(get("/api/bend-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBendHistMockMvc.perform(get("/api/bend-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBendHist() throws Exception {
        // Get the bendHist
        restBendHistMockMvc.perform(get("/api/bend-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBendHist() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        int databaseSizeBeforeUpdate = bendHistRepository.findAll().size();

        // Update the bendHist
        BendHist updatedBendHist = bendHistRepository.findById(bendHist.getId()).get();
        // Disconnect from session so that the updates on updatedBendHist are not directly saved in db
        em.detach(updatedBendHist);
        updatedBendHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .num(UPDATED_NUM)
            .radius(UPDATED_RADIUS)
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
        BendHistDTO bendHistDTO = bendHistMapper.toDto(updatedBendHist);

        restBendHistMockMvc.perform(put("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isOk());

        // Validate the BendHist in the database
        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeUpdate);
        BendHist testBendHist = bendHistList.get(bendHistList.size() - 1);
        assertThat(testBendHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testBendHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testBendHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testBendHist.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testBendHist.getRadius()).isEqualTo(UPDATED_RADIUS);
        assertThat(testBendHist.getDiameterOuterSteel()).isEqualTo(UPDATED_DIAMETER_OUTER_STEEL);
        assertThat(testBendHist.getInternalCoatThickness()).isEqualTo(UPDATED_INTERNAL_COAT_THICKNESS);
        assertThat(testBendHist.getExternalCoatThickness()).isEqualTo(UPDATED_EXTERNAL_COAT_THICKNESS);
        assertThat(testBendHist.getIsConcrCoating()).isEqualTo(UPDATED_IS_CONCR_COATING);
        assertThat(testBendHist.getConcrCoatThickness()).isEqualTo(UPDATED_CONCR_COAT_THICKNESS);
        assertThat(testBendHist.getConcrCoatDensity()).isEqualTo(UPDATED_CONCR_COAT_DENSITY);
        assertThat(testBendHist.getMeasWallThickness()).isEqualTo(UPDATED_MEAS_WALL_THICKNESS);
        assertThat(testBendHist.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testBendHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testBendHist.getSmts()).isEqualTo(UPDATED_SMTS);
        assertThat(testBendHist.getSmys()).isEqualTo(UPDATED_SMYS);
        assertThat(testBendHist.getSdbm()).isEqualTo(UPDATED_SDBM);
        assertThat(testBendHist.getSdaf()).isEqualTo(UPDATED_SDAF);
        assertThat(testBendHist.getSdcs()).isEqualTo(UPDATED_SDCS);
        assertThat(testBendHist.getAllowTensStrain()).isEqualTo(UPDATED_ALLOW_TENS_STRAIN);
        assertThat(testBendHist.getCorrosionAllowance()).isEqualTo(UPDATED_CORROSION_ALLOWANCE);
        assertThat(testBendHist.getFabricationAllowance()).isEqualTo(UPDATED_FABRICATION_ALLOWANCE);
        assertThat(testBendHist.getIsBurial()).isEqualTo(UPDATED_IS_BURIAL);
        assertThat(testBendHist.getBurialDepth()).isEqualTo(UPDATED_BURIAL_DEPTH);
        assertThat(testBendHist.getFactBurialDepth()).isEqualTo(UPDATED_FACT_BURIAL_DEPTH);
        assertThat(testBendHist.getDateManufactured()).isEqualTo(UPDATED_DATE_MANUFACTURED);
        assertThat(testBendHist.getMillTestPressure()).isEqualTo(UPDATED_MILL_TEST_PRESSURE);
        assertThat(testBendHist.getDesignPressure()).isEqualTo(UPDATED_DESIGN_PRESSURE);
        assertThat(testBendHist.getIncidentalPressure()).isEqualTo(UPDATED_INCIDENTAL_PRESSURE);
        assertThat(testBendHist.getDateInstalled()).isEqualTo(UPDATED_DATE_INSTALLED);
        assertThat(testBendHist.getSeamOrient()).isEqualTo(UPDATED_SEAM_ORIENT);
        assertThat(testBendHist.getSeamAngle()).isEqualTo(UPDATED_SEAM_ANGLE);
        assertThat(testBendHist.getAzimuth()).isEqualTo(UPDATED_AZIMUTH);
        assertThat(testBendHist.getSeabInstallTemp()).isEqualTo(UPDATED_SEAB_INSTALL_TEMP);
        assertThat(testBendHist.getVerticalAngle()).isEqualTo(UPDATED_VERTICAL_ANGLE);
        assertThat(testBendHist.getHeatTreatDurat()).isEqualTo(UPDATED_HEAT_TREAT_DURAT);
        assertThat(testBendHist.getMaxDesignTemp()).isEqualTo(UPDATED_MAX_DESIGN_TEMP);
        assertThat(testBendHist.getHeatNumber()).isEqualTo(UPDATED_HEAT_NUMBER);
        assertThat(testBendHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testBendHist.getDesignCoord()).isEqualTo(UPDATED_DESIGN_COORD);
        assertThat(testBendHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testBendHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testBendHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testBendHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBendHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testBendHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testBendHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testBendHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testBendHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingBendHist() throws Exception {
        int databaseSizeBeforeUpdate = bendHistRepository.findAll().size();

        // Create the BendHist
        BendHistDTO bendHistDTO = bendHistMapper.toDto(bendHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBendHistMockMvc.perform(put("/api/bend-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bendHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BendHist in the database
        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBendHist() throws Exception {
        // Initialize the database
        bendHistRepository.saveAndFlush(bendHist);

        int databaseSizeBeforeDelete = bendHistRepository.findAll().size();

        // Delete the bendHist
        restBendHistMockMvc.perform(delete("/api/bend-hists/{id}", bendHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<BendHist> bendHistList = bendHistRepository.findAll();
        assertThat(bendHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BendHist.class);
        BendHist bendHist1 = new BendHist();
        bendHist1.setId(1L);
        BendHist bendHist2 = new BendHist();
        bendHist2.setId(bendHist1.getId());
        assertThat(bendHist1).isEqualTo(bendHist2);
        bendHist2.setId(2L);
        assertThat(bendHist1).isNotEqualTo(bendHist2);
        bendHist1.setId(null);
        assertThat(bendHist1).isNotEqualTo(bendHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BendHistDTO.class);
        BendHistDTO bendHistDTO1 = new BendHistDTO();
        bendHistDTO1.setId(1L);
        BendHistDTO bendHistDTO2 = new BendHistDTO();
        assertThat(bendHistDTO1).isNotEqualTo(bendHistDTO2);
        bendHistDTO2.setId(bendHistDTO1.getId());
        assertThat(bendHistDTO1).isEqualTo(bendHistDTO2);
        bendHistDTO2.setId(2L);
        assertThat(bendHistDTO1).isNotEqualTo(bendHistDTO2);
        bendHistDTO1.setId(null);
        assertThat(bendHistDTO1).isNotEqualTo(bendHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bendHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bendHistMapper.fromId(null)).isNull();
    }
}
