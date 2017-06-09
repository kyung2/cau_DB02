
from django.shortcuts import render

from django.http import HttpResponseRedirect, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from dbTeam.models import *
import json
from django.core import serializers
from django.db.models import Avg
from django.db import connection
global str

from django.db.models import Prefetch
# Create your views here.


@csrf_exempt
def dictFetchall(cursor):
    columns = [col[0] for col in cursor.description]
    return [
        dict(zip(columns, row))
        for row in cursor.fetchall()
    ]

@csrf_exempt
def id_check(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    tempJson = {}
    try :
        check = user.objects.get(user_id=data['user_id'])
    except user.DoesNotExist:
        tempJson['result'] = "user_id is not define"
        tempJson['preschool'] = ''
        return HttpResponse(json.dumps(tempJson))
    if (check.user_pw.__eq__(data['pw'])):
        tempJson['result'] = "success"
        if (check.preSchool_id is None):
            tempJson['preschool'] = ''
            return HttpResponse(json.dumps(tempJson))
        tempJson['preschool'] = check.preSchool_id_id
        return HttpResponse(json.dumps(tempJson))
    else:
        tempJson['result'] = " pw error"
        tempJson['preschool'] = ''
        return HttpResponse(json.dumps(tempJson))


@csrf_exempt
def insert_user_info(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    tempJson = {}
    if user.objects.filter(user_id=data['user_id']).exists():
        tempJson['result'] = 'ALREADY'
        return HttpResponse(json.dumps(tempJson))
    else:
        user.objects.create(user_id=data['user_id'], user_pw=data['pw'], nick_name=data['nick_name'],
                            child_sex = data['child_sex'], child_age = data['child_age'],
                            preSchool_id_id=data['preschool_id'], si_do=data['si_do'], si_gun_gu=data['si_gun_gu'])
        tempJson['result'] = 'success'
    return HttpResponse(json.dumps(tempJson))

@csrf_exempt
def mypage(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    my = user.objects.select_related('preSchool_id').get(id=data['id'])
    my_info = {}
    my_info['user_id'] = my.user_id
    my_info['nick_name'] = my.nick_name
    my_info['child_sex'] = my.child_sex
    my_info['child_age'] = my.child_age
    my_info['si_do'] = my.si_do
    my_info['si_gun_gu'] = my.si_gun_gu
    if my.preSchool_id is None:
        my_info['preschool_name'] = ""
    else:
        my_info['preschool_name'] = my.preSchool_id.name
    return HttpResponse(json.dumps(my_info))

@csrf_exempt
def preschool_simple_info(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    preschool_info = {}
    try:
        preschool = preSchool.objects.values('name','si_do','si_gun_gu','tel').get(id=data['id'])
    except preSchool.DoesNotExist:
        preschool_info['result'] = "error"
        return HttpResponse("error")
    preschool_info['name'] = preschool['name']
    preschool_info['si_do'] = preschool['si_do']
    preschool_info['si_gun_gu'] = preschool['si_gun_gu']
    preschool_info['tel'] = preschool['tel']
    return HttpResponse(json.dumps({'preschool': preschool_info}), content_type='application/json')

@csrf_exempt
def preschool_gps(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    lat = data['latitude']
    long = data['longitude']
    maxLat = lat + 0.011
    minLat = lat - 0.011
    maxLong = long + 0.0088
    minLong = long - 0.0088
    preschool_list = preSchool.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)).values('id','name','si_do','si_gun_gu','tel','latitude','longitude')[:20]
    #preschool_list = serializers.serialize('json', list(preschool))
    preschools = []
    for preschool in preschool_list:
        preschools.append(preschool)
    return HttpResponse(json.dumps( preschools))


@csrf_exempt
def preschool_sigungu(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    preschool_list = preSchool.objects.filter(si_do=data['si_do']).filter(si_gun_gu=data['si_gun_gu']).values('id','name',
                                                                                                         'si_do',
                                                                                                         'si_gun_gu',
                                                                                                         'tel')
    preschools = []
    for preschool in preschool_list:
        preschools.append(preschool)
    return HttpResponse(json.dumps({'preschool': preschools}), content_type='application/json')

@csrf_exempt
def preschool_sido(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    preschool_list = preSchool.objects.filter(si_do=data['si_do']).values('id','name','si_do','si_gun_gu','tel')[:20]
    preschools = []
    for preschool in preschool_list:
        preschools.append(preschool)
    return HttpResponse(json.dumps({'preschool': preschools}), content_type='application/json')

@csrf_exempt
def preschool_teacher(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    teacher_info = teacher.objects.filter(kindergarten_id=data['id'])
    teacher_list = serializers.serialize('json', teacher_info)
    return HttpResponse(json.dumps({'teacher': teacher_list}), content_type='application/json')


@csrf_exempt
def preschool_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
#        my_prefetch = Prefetch('teacher', to_attr="teachers").prefetch_related(my_prefetch)
        preschool = preSchool.objects.filter(id=data['id'])
    except preSchool.DoesNotExist:
        preschool_info = {}
        return HttpResponse(json.dumps({'preschool': preschool_info}), content_type='application/json')

    return HttpResponse(json.dumps({'preschool': preschool}), content_type='application/json')

@csrf_exempt
def preschool_evaluation(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    evaluation_list = preSchoolEvaluation.objects.filter(preSchool_id=data['id']).exclude(comment__isnull=True).values('comment','comment_date')
    grade = preSchoolEvaluation.objects.filter(preSchool_id=data['id']).exclude(grade__isnull=True).aggregate(Avg('grade'))
    evaluation = []
    for ev in evaluation_list:
        ev['comment_date'] = str(ev['comment_date'])
        evaluation.append(ev)
    return HttpResponse(json.dumps({'evaluation': evaluation,'grade' : grade}), content_type='application/json')

@csrf_exempt
def preschool_enroll(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    tempJson = {}
    if preSchool.objects.filter(id=data['preschool_id']):
        try:
            user_info = user.objects.get(id=data['user_id'])
            user_info.preSchool_id_id = data['preschool_id']
            user_info.save()
            tempJson['result'] = 'OK'
            return HttpResponse(json.dumps(tempJson))
        except user.DoesNotExist:
            tempJson['result'] = 'User is not found'
            return HttpResponse(json.dumps(tempJson))
    tempJson['result'] = 'preschool is not found'
    return HttpResponse(json.dumps(tempJson))

@csrf_exempt
def kidscafe_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    kidscafe_list = kidsCafe.objects.filter(latitude__range=(minLat, maxLat),
                                              longitude__range=(minLong, maxLong)).values('id', 'name', 'si_do',
                                                                                          'si_gun_gu', 'facility_size',
                                                                                          'latitude', 'longitude')[:20]
    # preschool_list = serializers.serialize('json', list(preschool))
    kidscafes = []
    for kidscafe in kidscafe_list:
        kidscafes.append(kidscafe)
    return HttpResponse(json.dumps({'kidscafe': kidscafes}), content_type='application/json')


@csrf_exempt
def kidscafe_sigungu(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    kidscafe_list = kidsCafe.objects.filter(si_do=data['si_do']).filter(si_gun_gu=data['si_gun_gu']).values('id','name',
                                                                                                         'si_do',
                                                                                                         'si_gun_gu',
                                                                                                        'facility_size')
    kidscafes = []
    for kidscafe in kidscafe_list:
        kidscafes.append(kidscafe)
    return HttpResponse(json.dumps({'kidscafe': kidscafes}), content_type='application/json')


@csrf_exempt
def kidscafe_sido(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    kidscafe_list = kidsCafe.objects.filter(si_do=data['si_do']).values('id','name','si_do','si_gun_gu','facility_size')[:20]
    kidscafes = []
    for kidscafe in kidscafe_list:
        kidscafes.append(kidscafe)
    return HttpResponse(json.dumps({'kidscafe': kidscafes}), content_type='application/json')

@csrf_exempt
def kidscafe_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        kidscafe = kidsCafe.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        kidscafe_info = {}
        return HttpResponse(json.dumps({'kidscafe': kidscafe_info}), content_type='application/json')
    kidscafe_info = serializers.serialize('json', kidscafe)
    return HttpResponse(json.dumps({'kidscafe': kidscafe_info}), content_type='application/json')

@csrf_exempt
def kidscafe_evaluation(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    evaluation_list = kidsCafeEvaluation.objects.filter(kids_cafe_id=data['id']).exclude(comment__isnull=True).values('comment','comment_date')
    grade = kidsCafeEvaluation.objects.filter(kids_cafe_id=data['id']).exclude(grade__isnull=True).aggregate(Avg('grade'))
    evaluation = []
    for ev in evaluation_list:
        ev['comment_date'] = str(ev['comment_date'])
        evaluation.append(ev)
    return HttpResponse(json.dumps({'evaluation': evaluation, 'grade': grade}), content_type='application/json')

@csrf_exempt
def kidscafe_user_evaluation(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    tempJson = {}
    kidsCafeEvaluation.objects.create(user_id_id=data['user_id'], kids_cafe_id_id=data['kidscafe_id'], grade=data['grade'],
                                       grade_date=data['grade_date'], comment=data['comment'],
                                       comment_date=data['comment_date'])
    tempJson['result'] = 'success'
    return HttpResponse(json.dumps(tempJson))


@csrf_exempt
def preschool_user_evaluation(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    tempJson = {}
    preSchoolEvaluation.objects.create(user_id_id=data['user_id'], preSchool_id_id=data['kidscafe_id'], grade=data['grade'],
                                       grade_date=data['grade_date'], comment=data['comment'],
                                       comment_date=data['comment_date'])
    tempJson['result'] = 'success'
    return HttpResponse(json.dumps(tempJson))


@csrf_exempt
def kidscenter_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    kidscenter_list = kidsCenter.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    kidscenters = []
    for kidscenter in kidscenter_list:
        kidscenters.append(kidscenter)
    return HttpResponse(json.dumps({'kidscenter': kidscenters}), content_type='application/json')

@csrf_exempt
def kidscenter_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        kidscenter = kidsCenter.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        kidscenter_info = {}
        return HttpResponse(json.dumps({'kidscenter': kidscenter_info}), content_type='application/json')
    kidscenter_info = serializers.serialize('json', kidscenter)
    return HttpResponse(json.dumps({'kidscenter': kidscenter_info}), content_type='application/json')


@csrf_exempt
def elemschool_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    elemschool_list = elemSchool.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    elemschools = []
    for elemschool in elemschool_list:
        elemschools.append(elemschool)
    return HttpResponse(json.dumps({'elemschool': elemschools}), content_type='application/json')

@csrf_exempt
def elemschool_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        elemschool = elemSchool.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        elemschool_info = {}
        return HttpResponse(json.dumps({'elemschool': elemschool_info}), content_type='application/json')
    elemschool_info = serializers.serialize('json', elemschool)
    return HttpResponse(json.dumps({'elemschool': elemschool_info}), content_type='application/json')


@csrf_exempt
def childcarecenter_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    childcarecenter_list = childCareCenter.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    childcarecenters = []
    for childcarecenter in childcarecenter_list:
        childcarecenters.append(childcarecenter)
    return HttpResponse(json.dumps({'childcarecenter': childcarecenters}), content_type='application/json')


@csrf_exempt
def childcarecenter_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        childcarecenter = childCareCenter.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        childcarecenter_info = {}
        return HttpResponse(json.dumps({'childcarecenter': childcarecenter_info}), content_type='application/json')
    childcarecenter_info = serializers.serialize('json', childcarecenter)
    return HttpResponse(json.dumps({'childcarecenter': childcarecenter_info}), content_type='application/json')


@csrf_exempt
def safearea_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    safearea_list = childCareCenter.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    safeareas = []
    for safearea in safearea_list:
        safeareas.append(safearea)
    return HttpResponse(json.dumps({'safearea': safeareas}), content_type='application/json')

@csrf_exempt
def safearea_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        safearea = safeArea.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        safearea_info = {}
        return HttpResponse(json.dumps({'safearea': safearea_info}), content_type='application/json')
    safearea_info = serializers.serialize('json', safearea)
    return HttpResponse(json.dumps({'safearea': safearea_info}), content_type='application/json')


@csrf_exempt
def hospital_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    hospital_list = hospital.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    hospitals = []
    for hos in hospital_list:
        hospitals.append(hos)
    return HttpResponse(json.dumps({'hospital': hospitals}), content_type='application/json')


@csrf_exempt
def hospital_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        hospital_ = hospital.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        hospital_info = {}
        return HttpResponse(json.dumps({'hospital': hospital_info}), content_type='application/json')
    hospital_info = serializers.serialize('json', hospital_)
    return HttpResponse(json.dumps({'hospital': hospital_info}), content_type='application/json')


@csrf_exempt
def walfareservice_gps(request):
    walfareservice_list = walfareService.objects.all().\
        values('id','name','center_name','operator','operation_org')[:20]
    walfareservices = []
    for walfareservice in walfareservice_list:
        walfareservices.append(walfareservice)
    return HttpResponse(json.dumps({'walfareservice': walfareservices}), content_type='application/json')

@csrf_exempt
def walfareservice_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        walfareservice = walfareService.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        walfareservice_info = {}
        return HttpResponse(json.dumps({'walfareservice': walfareservice_info}), content_type='application/json')
    walfareservice_info = serializers.serialize('json', walfareservice)
    return HttpResponse(json.dumps({'walfareservice': walfareservice_info}), content_type='application/json')


@csrf_exempt
def trafficaccidentarea_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    trafficaccidentarea_list = trafficAccidentArea.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'si_do', 'si_gun_gu', 'law_code',"near_school", 'latitude', 'longitude')[:20]
    trafficaccidentareas = []
    for trafficaccidentarea in trafficaccidentarea_list:
        trafficaccidentareas.append(trafficaccidentarea)
    return HttpResponse(json.dumps({'trafficaccidentarea': trafficaccidentareas}), content_type='application/json')

@csrf_exempt
def trafficaccidentarea_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        trafficaccidentarea = trafficAccidentArea.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        trafficaccidentarea_info = {}
        return HttpResponse(json.dumps({'trafficaccidentarea': trafficaccidentarea_info}), content_type='application/json')
        trafficaccidentarea_info = serializers.serialize('json', trafficaccidentarea)
    return HttpResponse(json.dumps({'trafficaccidentarea': trafficaccidentarea_info}), content_type='application/json')


@csrf_exempt
def playfacility_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.11
    minLat = lat - 0.11
    maxLong = long + 0.088
    minLong = long - 0.088
    playfacility_list = playFacility.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'install_place', 'latitude', 'longitude')[:20]
    playfacilitys = []
    for playfacility in playfacility_list:
        playfacilitys.append(playfacility)
    return HttpResponse(json.dumps({'playfacility': playfacilitys}), content_type='application/json')

@csrf_exempt
def playfacility_detail(request):
    tempdata = request.POST.get('data')
    data = json.loads(tempdata)
    try:
        playfacility = playFacility.objects.filter(id=data['id'])
    except kidsCafe.DoesNotExist:
        playfacility_info = {}
        return HttpResponse(json.dumps({'playfacility': playfacility_info}), content_type='application/json')
    playfacility_info = serializers.serialize('json', playfacility)
    return HttpResponse(json.dumps({'playfacility': playfacility_info}), content_type='application/json')

@csrf_exempt
def func1(request):
    cursor = connection.cursor()
    cursor.execute('select name, address, type ,tel from `dbTeam_preschool` where school_bus="운영"') # 통학버스 운영하는 유치원
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "주  소 : " + f_list.get('address') + "\n"
        text += "전화번호 : " + f_list.get('tel') + "\n"
        text += "\n\n"
    return HttpResponse(text)


@csrf_exempt
def func2(request):
    cursor = connection.cursor()
    cursor.execute('select name, type, NumofCCTV from `dbTeam_preschool` where si_gun_gu = "안양시"') # 안양시 유치원의 cctv개수
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "CCTV수 : " + str(f_list.get('NumofCCTV')) + "\n"
        text += "\n\n"
    return HttpResponse(text)


@csrf_exempt
def func3(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, accident_injuryordeath, indoor_outdoor, accident_cause, accident_type FROM `dbTeam_accident` as ac inner join `dbTeam_preschool` as pr on ac.accident_where_id = pr.id where ac.accident_date > date(subdate(now(),Interval 365 DAY)) order by pr.id asc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)


@csrf_exempt
def func4(request):
    cursor = connection.cursor()
    cursor.execute('SELECT p.name, p.type, p.tel, p.address,count(p.id) as coun FROM `dbTeam_teacher` as t inner join `dbTeam_preschool` as p on t.kindergarten_id = p.id group by p.id order by coun desc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화 번호 : " + f_list.get('tel') + "\n"
        text += "선생님 수 : " + str(f_list.get('coun')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func5(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, address,tel, count(p.id) as coun from  `dbTeam_accident` as a inner join `dbTeam_preschool` as p on a.accident_where_id = p.id group by p.id order by coun asc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화 번호 : " + f_list.get('tel') + "\n"
        text += "사고/사망 수 : " + str(f_list.get('coun')) + "\n"
        text += "\n\n"
    return HttpResponse(text)



@csrf_exempt
def func6(request):
    cursor = connection.cursor()
    cursor.execute('SELECT count(comment) as coun, ps.id, ps.name, ps.type, ps.address, ps.tel FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_preschool`as ps on pe.preSchool_id_id = ps.id group by ps.id order by coun DESC')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화 번호 : " + f_list.get('tel') + "\n"
        text += "댓글 수 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT comment, nick_name FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_user`as u on pe.user_id_id = u.id where pe.preSchool_id_id = ' + str(f_list.get('id')))
        comment_list = dictFetchall(cursor)
        for comment in comment_list:
            text += "\t" + comment.get('nick_name') + " : " + comment.get('comment')
        text += "\n\n"
    return HttpResponse(text)


@csrf_exempt
def func7(request):
    cursor = connection.cursor()
    cursor.execute('SELECT avg(grade) as coun, ps.id, ps.name, ps.type, ps.address, ps.tel FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_preschool`as ps on pe.preSchool_id_id = ps.id group by ps.id order by coun desc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화번호 : " + f_list.get('tel') + "\n"
        text += " 평  점 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT grade, nick_name FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_user`as u on pe.user_id_id = u.id where pe.preSchool_id_id = ' + str(f_list.get('id')))
        g_list = dictFetchall(cursor)
        for grade in g_list:
            text += "\t" + grade.get('nick_name') + " : " + grade.get('grade') + " 점"
        text += "\n\n"

    return HttpResponse(text)

@csrf_exempt
def func8(request):
    cursor = connection.cursor()
    cursor.execute('SELECT count(comment) as coun, ps.id, ps.name, facility_size, ps.operation_state FROM `dbTeam_kidscafeevaluation` as ke inner join `dbTeam_kidscafe`as ps on ke.kids_cafe_id_id = ps.id group by ps.id order by coun DESC')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') +"\n"
        text += " 주  소 : " + f_list.get('operation_state') + "\n"
        text += " 크  기 : " + str(f_list.get('facility_size')) + "\n"
        text += "댓글 수 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT comment, nick_name FROM `dbTeam_kidscafeevaluation` as pe inner join `dbTeam_user`as u on pe.user_id_id = u.id where pe.kids_cafe_id_id = ' + str(
                f_list.get('id')))
        comment_list = dictFetchall(cursor)
        for comment in comment_list:
            text += "\t" + comment.get('nick_name') + " : " + comment.get('comment')
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func9(request):
    cursor = connection.cursor()
    cursor.execute(
        'SELECT avg(grade) as coun, k.id, k.name, k.facility_size, k.operation_state FROM `dbTeam_kidscafeevaluation` as ke inner join `dbTeam_kidscafe`as k on ke.kids_cafe_id_id = k.id group by k.id order by coun desc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') +"\n"
        text += " 주  소 : " + f_list.get('operation_state') + "\n"
        text += " 크  기 : " + str(f_list.get('facility_size')) + "\n"
        text += " 평  점 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT grade, nick_name FROM `dbTeam_kidscafeevaluation` as ke inner join `dbTeam_user`as u on ke.user_id_id = u.id where kids_cafe_id_id = ' + str(f_list.get('id')))
        g_list = dictFetchall(cursor)
        for grade in g_list:
            text += "\t" + grade.get('nick_name') + " : " + grade.get('grade') + " 점"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func10(request):
    cursor = connection.cursor()
    cursor.execute(
        'SELECT avg(grade) as coun, k.id, k.name, k.facility_size, k.operation_state FROM `dbTeam_kidscafeevaluation` as ke inner join `dbTeam_kidscafe`as k on ke.kids_cafe_id_id = k.id group by k.id order by coun asc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "\n"
        text += " 주  소 : " + f_list.get('operation_state') + "\n"
        text += " 크  기 : " + str(f_list.get('facility_size')) + "\n"
        text += " 평  점 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT grade, nick_name FROM `dbTeam_kidscafeevaluation` as ke inner join `dbTeam_user`as u on ke.user_id_id = u.id where kids_cafe_id_id = ' + str(
                f_list.get('id')))
        g_list = dictFetchall(cursor)
        for grade in g_list:
            text += "\t" + grade.get('nick_name') + " : " + grade.get('grade') + " 점"
        text += "\n\n"
        return HttpResponse(text)

@csrf_exempt
def func11(request):
    cursor = connection.cursor()
    cursor.execute('SELECT avg(grade) as coun, ps.id, ps.name, ps.type, ps.address, ps.tel FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_preschool`as ps on pe.preSchool_id_id = ps.id group by ps.id order by coun asc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화번호 : " + f_list.get('tel') + "\n"
        text += " 평  점 : " + str(f_list.get('coun')) + "\n"
        cursor.execute(
            'SELECT grade, nick_name FROM `dbTeam_preschoolevaluation` as pe inner join `dbTeam_user`as u on pe.user_id_id = u.id where pe.preSchool_id_id = ' + str(f_list.get('id')))
        g_list = dictFetchall(cursor)
        for grade in g_list:
            text += "\t" + grade.get('nick_name') + " : " + grade.get('grade') + " 점"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func12(request):
    cursor = connection.cursor()
    cursor.execute('SELECT , accident_injuryordeath, indoor_o [...] pr on ac.accident_where_id = pr.id group by pr.id')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func13(request):
    cursor = connection.cursor()
    cursor.execute('SELECT count(si_do) as coun, si_do, tel,si_gun_gu FROM `dbTeam_preschool` where type = "국공립" group by si_do')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += " 시도 / 시군구 : " + f_list.get('si_do') + "/" + f_list.get('si_gun_gu') + "\n"
        text += " 국공립 수 : " + str(f_list.get('coun')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func14(request):
    cursor = connection.cursor()
    cursor.execute('SELECT count(near_school) as coun, si_do, near_school,si_gun_gu FROM `dbTeam_trafficaccidentarea` group by near_school order by coun desc')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += " 시도 / 시군구 : " + f_list.get('si_do') + "/" + f_list.get('si_gun_gu') + "\n"
        text += "근처 초등학교 : " + str(f_list.get('near_school')) + "\n"
        text += " 교통사고 수 : " + str(f_list.get('coun')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func15(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, accident_injuryordeath, indoor_o [...] pr on ac.accident_where_id = pr.id group by pr.id')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func16(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, accident_injuryordeath, indoor_o [...] pr on ac.accident_where_id = pr.id group by pr.id')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func17(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, accident_injuryordeath, indoor_o [...] pr on ac.accident_where_id = pr.id group by pr.id')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func18(request):
    cursor = connection.cursor()
    cursor.execute('SELECT name,type, accident_injuryordeath, indoor_o [...] pr on ac.accident_where_id = pr.id group by pr.id')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += "사고 / 사망 : " + str(f_list.get('accident_injuryordeath')) + "\n"
        text += "실내 / 실외 : " + str(f_list.get('indoor_outdoor')) + "\n"
        text += "사고 유형 : " + str(f_list.get('accident_cause')) + "\n"
        text += "사고 타입 : " + str(f_list.get('accident_type')) + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func19(request):
    cursor = connection.cursor()
    cursor.execute('SELECT p.name, p.tel, tq.detailed_type, p.address, p.type FROM `dbTeam_teacherqualification` as tq inner join `dbTeam_teacher` as t on tq.teacher_id_id = t.id inner join `dbTeam_preschool` as p on p.id = t.kindergarten_id where tq.detailed_type like "%장애%"')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화번호 : " + f_list.get('tel') + "\n"
        text += "자격증 : " + f_list.get('detailed_type') + "\n"
        text += "\n\n"
    return HttpResponse(text)

@csrf_exempt
def func20(request):
    cursor = connection.cursor()
    cursor.execute('SELECT p.name, p.capacity,p.tel, count(kindergarten_id) as coun, p.address, p.type FROM `dbTeam_teacher` as t inner join `dbTeam_preschool` as p on t.kindergarten_id = p.id where p.capacity >=50 group by kindergarten_id order by coun desc limit 20')
    list = dictFetchall(cursor)
    text = ""
    for f_list in list:
        text += "\t\t" + f_list.get('name') + "(" + f_list.get('type') + ")\n"
        text += " 주  소 : " + f_list.get('address') + "\n"
        text += "전화번호 : " + f_list.get('tel') + "\n"
        text += "정 원 : " + str(f_list.get('capacity')) + " / "
        text += "선생님 수: " + str(f_list.get('coun')) + "\n"
        text += "\n\n"
    return HttpResponse(text)
