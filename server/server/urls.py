"""server URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.8/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from dbTeam import views

urlpatterns = [
    url(r'^login/checkID/(?P<pk>[Wd]+)$', views.id_check, name='id_check'),         # login id check
    url(r'^signup/checkID/(?P<pk>[Wd]+)$',views.id_check, name='id_check'),         # sign up id check
    url(r'^signup/insert/user/(?P<pk>[Wd]+)$',views.insert_user_info, name='insert_user_info'),

    url(r'^preschool/info/$', views.prechool, name='preschool'),                    # 유치원 전체 정보

    url(r'^preschoo/info/simple/(?P<pk>[Wd]+)/$', views.prechool_simple_info, name='prechool_simple_info'), # 유치원 간단한 정보
]
