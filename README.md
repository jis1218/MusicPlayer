##### All Fragment-to-Fragment communication is done through the associated Activity. Two Fragments should never communicate directly.
##### 프래그먼트 간에는 서로 상호작용을 할 수 없음, 꼭 Activity를 거쳐야 한다.

##### To allow a Fragment to communicate up to its Activity, you can define an interface in the Fragment class and implement it within the Activity. The Fragment captures the interface implementation during its onAttach() lifecycle method and can then call the Interface methods in order to communicate with the Activity.

##### 프래그먼트에서 어댑터로 data를 넘겨주지 않는 이유
##### 프래그먼트가 여러개 일 경우 프래그먼트마다 데이터를 넘겨주는 것 보다 activity를 통해 데이터를 넘겨주는 것이 더 효율적이다.
##### activity를 통해 넘겨주는 것은 interface를 구현함으로써 가능하다

##### MediaPlayer는 스트림 형식으로 파일을 읽는다.
##### MediaPlayer.create()는 해당 uri 파일에 빨대를 꽂아 스트림을 읽어드린다.
##### MediaPlayer.release()는 스트림을 해제한다.
