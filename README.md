##### All Fragment-to-Fragment communication is done through the associated Activity. Two Fragments should never communicate directly.
##### 프래그먼트 간에는 서로 상호작용을 할 수 없음, 꼭 Activity를 거쳐야 한다.

##### To allow a Fragment to communicate up to its Activity, you can define an interface in the Fragment class and implement it within the Activity. The Fragment captures the interface implementation during its onAttach() lifecycle method and can then call the Interface methods in order to communicate with the Activity.

##### 프래그먼트에서 어댑터로 data를 넘겨주지 않는 이유
##### 프래그먼트가 여러개 일 경우 프래그먼트마다 데이터를 넘겨주는 것 보다 activity를 통해 데이터를 넘겨주는 것이 더 효율적이다.
##### activity를 통해 넘겨주는 것은 interface를 구현함으로써 가능하다

##### MediaPlayer는 스트림 형식으로 파일을 읽는다.
##### MediaPlayer.create()는 해당 uri 파일에 빨대를 꽂아 스트림을 읽어드린다.
##### MediaPlayer.release()는 스트림을 해제한다.

##### 하면서 안되는 것들
##### 1. list에서 음악을 눌러 실행시킨 후 뒤로 간 후에 다른 것을 눌러 실행시키면 음악이 두개가 나옴
##### 2. 앱을 껐다 다시 키면 리스트가 중복되어 늘어나있음
##### 3. 싱글톤 만들어 사용하기
##### 4. 앱을 껐다 다시 키면 음악이 완료되었을 때 다음 곡으로 잘 넘어가지 않음

##### Activity에서 Context를 Pending Intent에 담아 서비스에 보내면
http://techlog.gurucat.net/80

##### PendingIntent에 대한 android 설명
##### A description of an Intent and target action to perform with it. Instances of this class are created with getActivity(Context, int, Intent, int), getActivities(Context, int, Intent[], int), getBroadcast(Context, int, Intent, int), and getService(Context, int, Intent, int); the returned object can be handed to other applications so that they can perform the action you described on your behalf at a later time.
