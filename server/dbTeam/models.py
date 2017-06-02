from django.db import models


# Create your models here.

class preSchool(models.Model):
    name = models.CharField(max_length=45)
    type = models.DateField()
    permit_date = models.CharField(max_length=11)
    pause_start_date = models.CharField(max_length=11)
    pause_end_date = models.CharField(max_length=11)
    close_date = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    operation_state = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    nursing_room_area = models.CharField(max_length=11)
    assess_certification_type = models.CharField(max_length=11)
    characteristic = models.CharField(max_length=11)
    delegation_enterprise_type = models.CharField(max_length=11)
    delegation_enterprise_name = models.CharField(max_length=11)
    fireInsurance = models.CharField(max_length=11)
    NumofCCTV = models.CharField(max_length=11)


class accident(models.Model):
    data = models.CharField(max_length=45)
    accident_death = models.DateField()
    accident_type = models.CharField(max_length=11)
    indoor_outdoor = models.CharField(max_length=11)
    accident_cause = models.CharField(max_length=11)
    accident_where = models.CharField(max_length=11)


class teacher(models.Model):
    sex = models.CharField(max_length=45)
    kindergarten = models.DateField()
    type = models.CharField(max_length=11)

class teacherQualification(models.Model):
    teacher_id = models.CharField(max_length=45)
    teacher_qualification = models.DateField()
    detailed_type = models.CharField(max_length=11)

class qualification(models.Model):
    teacher_qualification = models.DateField()
    detailed_type = models.CharField(max_length=11)

class preSchoolEvaluation(models.Model):
    user_id = models.DateField()
    preSchool_id = models.CharField(max_length=11)
    grade = models.CharField(max_length=45)
    grade_date = models.DateField()
    comment = models.CharField(max_length=11)
    comment_date = models.CharField(max_length=11)


class user(models.Model):
    user_id = models.DateField()
    user_pw = models.CharField(max_length=11)
    nick_name = models.CharField(max_length=45)
    kindergarten = models.DateField()
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)

class kidsCafeEvaluation(models.Model):
    user_id = models.DateField()
    kids_cafe_id = models.CharField(max_length=11)
    grade = models.CharField(max_length=45)
    grade_date = models.DateField()
    comment = models.CharField(max_length=11)
    comment_date = models.CharField(max_length=11)

class kidsCafe(models.Model):
    name = models.CharField(max_length=45)
    permit_date = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    operation_state = models.CharField(max_length=11)
    mutiple_use = models.CharField(max_length=11)
    facility_size = models.CharField(max_length=11)
    hygiene_name = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class kidsCenter(models.Model):
    name = models.CharField(max_length=45)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    capacity = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class kidsCenter(models.Model):
    name = models.CharField(max_length=45)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    capacity = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)

class elemSchool(models.Model):
    name = models.CharField(max_length=45)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    homepage = models.CharField(max_length=11)
    public_private = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class childCareCenter(models.Model):
    name = models.CharField(max_length=45)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class safeArea(models.Model):
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    adress = models.CharField(max_length=11)
    facility_name= models.CharField(max_length=11)
    management_agency = models.CharField(max_length=11)
    police_office = models.CharField(max_length=11)
    NumofCCTV = models.CharField(max_length=11)
    road_width = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class hospital(models.Model):
    name = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu= models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    designated_date = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class walfareService(models.Model):
    name = models.CharField(max_length=11)
    purpose = models.CharField(max_length=11)
    center_num = models.CharField(max_length=11)
    center_name = models.CharField(max_length=11)
    operator = models.CharField(max_length=11)
    operation_org = models.CharField(max_length=11)
    op_start_data = models.CharField(max_length=11)
    op_end_date = models.CharField(max_length=11)


class trafficAccidentArea(models.Model):
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    law_code = models.CharField(max_length=11)
    police_office = models.CharField(max_length=11)
    near_school = models.CharField(max_length=11)
    occur_cnt = models.CharField(max_length=11)
    death_cnt = models.CharField(max_length=11)
    serious_cnt = models.CharField(max_length=11)
    light_cnt = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)


class playPacility(models.Model):
    name = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    install_date = models.CharField(max_length=11)
    install_place = models.CharField(max_length=11)
    public_private = models.CharField(max_length=11)
    indoor_outdoor = models.CharField(max_length=11)
    is_excellent = models.CharField(max_length=11)
    longitude = models.CharField(max_length=11)
    latitude = models.CharField(max_length=11)