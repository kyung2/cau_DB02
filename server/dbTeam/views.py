
from django.shortcuts import render

from django.http import HttpResponseRedirect, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from dbTeam.models import *
import json
from django.core import serializers
from django.db.models import Avg
# Create your views here.

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
                            preSchool_id_id=data['preschool_id'], si_do=data['si_do'], si_gun_gu=data['si_gun_gu'])
        tempJson['result'] = 'success'
    return HttpResponse(json.dumps(tempJson))

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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
        preschool = preSchool.objects.filter(id=data['id'])
    except preSchool.DoesNotExist:
        preschool_info = {}
        return HttpResponse(json.dumps({'preschool': preschool_info}), content_type='application/json')
    pre_list = serializers.serialize('json', preschool)
    return HttpResponse(json.dumps({'preschool': pre_list}), content_type='application/json')

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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
    kidscafe_list = preSchool.objects.filter(latitude__range=(minLat, maxLat),
                                              longitude__range=(minLong, maxLong)).values('id', 'name', 'si_do',
                                                                                          'si_gun_gu', 'tel',
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
def kidscenter_gps(request):
    tempdata = json.loads(request.POST.get('data'))
    lat = tempdata['latitude']
    long = tempdata['longitude']
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
    elemschool_list = elemSchool.objects.filter(latitude__range=(minLat, maxLat), longitude__range=(minLong, maxLong)). \
        values('id', 'name', 'si_do', 'si_gun_gu', 'tel', 'latitude', 'longitude')[:20]
    elemschools = []
    for elemschool in elemschool_list:
        elemschools.append(elemschool)
    return HttpResponse(json.dumps({'elemschool': elemschool}), content_type='application/json')

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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
        values('name','center_name','operator','operation_org')[:20]
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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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
    maxLat = lat + 0.55
    minLat = lat - 0.55
    maxLong = long + 0.44
    minLong = long - 0.44
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

