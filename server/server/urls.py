from django.conf.urls import url
from dbTeam import views

urlpatterns = [
    url(r'^login/', views.id_check, name='id_check'),         # login id check
    url(r'^signup/',views.insert_user_info, name='insert_user_info'), # sign up
    url(r'^mypage/',views.mypage, name='mypage'), # sign up

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

    url(r'^func1/', views.func1, name='func1'),  #  통학버스 운영하는 유치원
    url(r'^func2/', views.func2, name='func2'),  # 안양시 유치원의 cctv개수
    url(r'^func3/', views.func3, name='func3'),  # 1년 안의 유치원 사고사망
    url(r'^func4/', views.func4, name='func4'),  # 유치원 선생님 수 내림차순
    url(r'^func5/', views.func5, name='func5'),  # 유치원별 사고사망 수 오름차순

    url(r'^func6/', views.func6, name='func6'),  # 댓글 수가 많은 유차원 내림차순
    url(r'^func7/', views.func7, name='func7'),  # 평가가 높은 유치원 내림차순
    url(r'^func8/', views.func8, name='func8'),  # 댓글 수가 많은 키즈카페 내림차순
    url(r'^func9/', views.func9, name='func9'),  # 평가가 높은 키즈카페 내림차순
    url(r'^func10/', views.func10, name='func10'),  # 평가가 낮은 키즈카페 오름차순
    url(r'^func11/', views.func11, name='func11'),  # 평가가 낮은 유치원 오름차순

    url(r'^func12/', views.func12, name='func12'),  # 안양시 각 시설 2개씩 추천 - 시간 오래걸림 스킵 다른걸로 바꿀 예정

    url(r'^func13/', views.func13, name='func13'),  # 국공립 유치원 갯수
    url(r'^func14/', views.func14, name='func14'),  # 교통사고가 가장 많이 난 곳

    url(r'^func15/', views.func15, name='func15'),  # 평가가 낮은 키즈카페 오름차순 중복
    url(r'^func16/', views.func16, name='func16'),  # 평가가 낮은 유치원 오름차순 중복
    url(r'^func17', views.func17, name='func17'),  # 안양시 각 시설 2개씩 추천 중복

    url(r'^func18/', views.func18, name='func18'),  # 자격증이 2개 이상인 선생 list
    url(r'^func19/', views.func19, name='func19'),  # '장애', ' 특수교사' 를 가지고있는 선생이 있는 유치원
    url(r'^func20/', views.func20, name='func20'),  # 정원이 50명 이상인 유치원의 선생 수

]
