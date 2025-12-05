from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from bs4 import BeautifulSoup
import pandas as pd
import time

wd = webdriver.Chrome()
result = []

for i in range(1, 2):

    wd.get("https://www.starbucks.co.kr/store/store_map.do")
    time.sleep(2)

    try:
        # jQuery 이벤트 바인딩 (실제로는 없어도 대부분 동작함)
        wd.execute_script('$(".quickResultLstCon").on("click", $.storemap.quickSearchClick);')
        time.sleep(1)

        # -----------------------------
        # ① 매장 리스트 요소 가져오기
        # -----------------------------
        store_list = wd.find_elements(By.CSS_SELECTOR, ".quickResultLstCon")

        if len(store_list) == 0:
            print("매장 리스트가 로드되지 않음")
            continue

        # -----------------------------
        # ② 첫 번째 매장 클릭
        # -----------------------------
        store_list[0].click()

        # -----------------------------
        # ③ 상세 정보 로드 대기
        # -----------------------------
        WebDriverWait(wd, 5).until(
            EC.presence_of_element_located((By.CSS_SELECTOR, "header.titl h6"))
        )

        html = wd.page_source
        soupCB = BeautifulSoup(html, 'html.parser')

        # -----------------------------
        # ④ 매장명 가져오기
        # -----------------------------
        store_name = soupCB.select_one("header.titl h6").get_text(strip=True)

        # -----------------------------
        # ⑤ 주소, 전화번호 가져오기
        # -----------------------------
        info_list = soupCB.select("p.result_details")

        # 구조상 0: 매장타입, 1: 영업시간, 2: 주소, 3: 전화번호
        store_address = info_list[2].get_text(strip=True)
        store_phone = info_list[3].get_text(strip=True)

        result.append([i, store_name, store_address, store_phone])
        print(i, store_name, store_address, store_phone)

    except Exception as e:
        print("오류:", e)
        continue

# CSV 저장
df = pd.DataFrame(result, columns=('no','store','address','phone'))
df.to_csv("Starbucks.csv", encoding="utf-8-sig", mode="w", index=False)
