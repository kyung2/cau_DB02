from django.db import models


# Create your models here.

class preSchool(models.Model):
    name = models.CharField(max_length=11)
    type = models.CharField(max_length=11)
    permit_date = models.DateField()
    pause_start_date = models.DateField
    pause_end_date = models.DateField()
    close_date = models.DateField()
    postcode = models.IntegerField()
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    latitude = models.FloatField()
    longitude = models.FloatField()
    operation_state = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    Fax = models.CharField(max_length=11)
    capacity = models.IntegerField()
    school_bus = models.CharField(max_length=11)
    playground_num = models.IntegerField()
    nursing_room_num = models.IntegerField()
    nursing_room_area = models.IntegerField()
    assess_certification_type = models.IntegerField()
    NumofCCTV = models.IntegerField()


class accident(models.Model):
    accident_date = models.DateField()
    accident_where = models.ForeignKey(preSchool)  # fk
    accident_injuryordeath = models.CharField(max_length=11)
    indoor_outdoor = models.CharField(max_length=11)
    accident_cause = models.CharField(max_length=11)
    accident_type = models.CharField(max_length=11)


class teacher(models.Model):
    kindergarten = models.ForeignKey(preSchool) #fk
    sex = models.CharField(max_length=11)
    type = models.CharField(max_length=11)

class qualification(models.Model):
    teacher_qualification = models.CharField(max_length=11) #pk
    detailed_type = models.CharField(max_length=11) #pk

class teacherQualification(models.Model):
    teacher_id = models.ForeignKey(teacher)  #pk
    teacher_qualification = models.ForeignKey(qualification) #pk #fk
    detailed_type = models.CharField(max_length=45) #pk #fk

class user(models.Model):
    user_id = models.CharField(max_length=11) #pk
    user_pw = models.CharField(max_length=11)
    nick_name = models.CharField(max_length=11)
    preSchool_id = models.CharField(max_length=11, null=True) #fk
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)

class preSchoolEvaluation(models.Model):
    user_id = models.ForeignKey(user, on_delete=models.CASCADE)  #pk #fk
    preSchool_id = models.ForeignKey(preSchool, on_delete=models.CASCADE) #pk #fk
    grade = models.CharField(max_length=11)
    grade_date = models.DateField()
    comment = models.CharField(max_length=11)
    comment_date = models.DateField()

class kidsCafe(models.Model):
    name = models.CharField(max_length=11)
    permit_date = models.DateField()
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    operation_state = models.CharField(max_length=11)
    mutiple_use = models.CharField(max_length=11)
    facility_size = models.IntegerField()
    hygiene_name = models.CharField(max_length=11)
    longitude = models.FloatField()
    latitude = models.FloatField()


class kidsCafeEvaluation(models.Model):
    user_id = models.ForeignKey(user, on_delete=models.CASCADE)  #pk #fk
    kids_cafe_id = models.ForeignKey(kidsCafe, on_delete=models.CASCADE) #pk #fk
    grade = models.CharField(max_length=11)
    grade_date = models.DateField()
    comment = models.CharField(max_length=11)
    comment_date = models.DateField()
##

class kidsCenter(models.Model):
    name = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    capacity = models.IntegerField()
    longitude = models.FloatField()
    latitude = models.FloatField()

class elemSchool(models.Model):
    name = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    homepage = models.CharField(max_length=11)
    public_private = models.CharField(max_length=11)
    longitude = models.FloatField()
    latitude = models.FloatField()


class childCareCenter(models.Model):
    name = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    longitude = models.FloatField()
    latitude = models.FloatField()


class safeArea(models.Model):
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    adress = models.CharField(max_length=11)
    facility_name= models.CharField(max_length=11)
    management_agency = models.CharField(max_length=11)
    police_office = models.CharField(max_length=11)
    NumofCCTV = models.IntegerField()
    road_width = models.IntegerField()
    postcode = models.CharField(max_length=11)
    longitude = models.FloatField()
    latitude = models.FloatField()


class hospital(models.Model):
    name = models.CharField(max_length=11)
    tel = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu= models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    designated_date = models.DateField()
    longitude = models.FloatField()
    latitude = models.FloatField()


class walfareService(models.Model):
    name = models.CharField(max_length=11)
    purpose = models.CharField(max_length=11)
    center_num = models.IntegerField()
    center_name = models.CharField(max_length=11)
    operator = models.CharField(max_length=11)
    operation_org = models.CharField(max_length=11)
    op_start_data = models.DateField()
    op_end_date = models.DateField()


class trafficAccidentArea(models.Model):
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    law_code = models.CharField(max_length=11)
    police_office = models.CharField(max_length=11)
    near_school = models.CharField(max_length=11)
    occur_cnt = models.IntegerField()
    death_cnt = models.IntegerField()
    serious_cnt = models.IntegerField()
    light_cnt = models.IntegerField()
    longitude = models.FloatField()
    latitude = models.FloatField()


class playPacility(models.Model):
    name = models.CharField(max_length=11)
    si_do = models.CharField(max_length=11)
    si_gun_gu = models.CharField(max_length=11)
    address = models.CharField(max_length=11)
    postcode = models.CharField(max_length=11)
    install_date = models.DateField()
    install_place = models.CharField(max_length=11)
    public_private = models.CharField(max_length=11)
    indoor_outdoor = models.CharField(max_length=11)
    is_excellent = models.CharField(max_length=11)
    longitude = models.FloatField()
    latitude = models.FloatField()