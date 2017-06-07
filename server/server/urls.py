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
    url(r'^login/', views.id_check, name='id_check'),         # login id check
    url(r'^signup/',views.insert_user_info, name='insert_user_info'), # sign up

    url(r'^preschool/simple/info', views.preschool_simple_info, name='preschool_simple_info'), # 유치원 간단한 정보_등록한 정보가 있는 경우
    url(r'^preschool/gps/', views.preschool_gps, name='preschool_gps'),         # GPS 기준 list
    url(r'^preschool/sigungu/', views.preschool_sigungu, name='preschool_sigungu'),     # sigungu 기준 list
    url(r'^preschool/sido/', views.preschool_sido, name='preschool_sido'),        # sido 기준 list
    url(r'^preschool/teacher/', views.preschool_teacher, name='preschool_teacher'),  # 유치원 선생님 정보
    url(r'^preschool/detail/', views.preschool_detail, name='preschool_detail'),      # 유치원 세부 사항, 선생님, 사고사망
    url(r'^preschool/evaluation/', views.preschool_evaluation, name='preschool_evaluation'),    # 유치원 평가 및 별점 정보
    url(r'^preschool/enroll/', views.preschool_enroll, name='preschool_enroll'),    # 내아이 유치원 등록
    url(r'^preschool/user/evaluation/', views.preschool_user_evaluation, name='preschool_user_evaluation'),  # 키즈카페 평가 및 별점 정보

    url(r'^kidsCafe/gps/', views.kidscafe_gps, name='kidscafe_gps'),         # GPS 기준 list
    url(r'^kidsCafe/sigungu/', views.kidscafe_sigungu, name='kidscafe_sigungu'),     # sigungu 기준 list
    url(r'^kidsCafe/sido/', views.kidscafe_sido, name='kidscafe_sido'),        # sido 기준 list
    url(r'^kidsCafe/detail/', views.kidscafe_detail, name='kidscafe_detail'),  # 키즈카페 세부 사항
    url(r'^kidsCafe/evaluation/', views.kidscafe_evaluation, name='kidscafe_evaluation'),  # 키즈카페 평가 및 별점 정보
    url(r'^kidsCafe/user/evaluation/', views.kidscafe_user_evaluation, name='kidscafe_user_evaluation'),  # 키즈카페 평가 및 별점 정보

    url(r'^kidsCenter/gps/', views.kidscenter_gps, name='kidscenter_gps'),         # GPS 기준 list
    url(r'^kidsCenter/detail/', views.kidscenter_detail, name='kidscenter_detail'),  # 센터 세부 사항

    url(r'^elemSchool/gps/', views.elemschool_gps, name='elemschool_gps'),  # GPS 기준 list
    url(r'^elemSchool/detail/', views.elemschool_detail, name='elemschool_detail'),  # 초등학교 세부 사항

    url(r'^childCareCenter/gps/', views.childcarecenter_gps, name='childcarecenter_gps'),  # GPS 기준 list
    url(r'^childCareCenter/detail/', views.childcarecenter_detail, name='childcarecenter_detail'),  # 돌봄기관 세부 사항

    url(r'^safeArea/gps/', views.safearea_gps, name='safearea_gps'),  # GPS 기준 list
    url(r'^safeArea/detail/', views.safearea_detail, name='safearea_detail'),  # 병원 세부 사항

    url(r'^hospital/gps/', views.hospital_gps, name='hospital_gps'),  # GPS 기준 list
    url(r'^hospital/detail/', views.hospital_detail, name='hospital_detail'),  # 병원 세부 사항

    url(r'^walfareService/gps/', views.walfareservice_gps, name='walfareservice_gps'),  # GPS 기준 list
    url(r'^walfareService/detail/', views.walfareservice_detail, name='walfareservice_detail'),  # 복지서비스 세부 사항

    url(r'^trafficAccidentArea/gps/', views.trafficaccidentarea_gps, name='trafficaccidentarea_gps'),  # GPS 기준 list
    url(r'^trafficAccidentArea/detail/', views.trafficaccidentarea_detail, name='trafficaccidentarea_detail'),  # 교통사고지역 세부 사항

    url(r'^playFacility/gps/', views.playfacility_gps, name='playfacility_gps'),  # GPS 기준 list
    url(r'^playFacility/detail/', views.playfacility_detail, name='playfacility_detail'),  # 놀이시설 세부 사항
]
