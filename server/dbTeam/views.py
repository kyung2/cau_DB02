
from django.shortcuts import render

from django.http import HttpResponseRedirect, HttpResponse
from dbTeam.models import *
# Create your views here.


def id_check(request):
#    return HttpResponse(json.dumps({'total': total_sum_list, 'usage': usage_sum_list}))
    return HttpResponse("ok")

def prechool(request):
    return HttpResponse("ok")

def prechool_simple_info(request):
    return HttpResponse("ok")


def insert_user_info(request):
    return HttpResponse("ok")

