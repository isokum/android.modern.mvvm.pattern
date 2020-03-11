# android.modern.mvvm.pattern
Google Architecture Component 를 이용해 Github 사용자 조회 샘플앱을 만들었다.

MVVM Pattern 에서 DataBinding 만 제거한 버전이라고 보면 된다.
사용한 기술은 Kotlin Coroutine, Dagger2, Retrofit2, LifeCycle LiveData, Room 을 사용했다.

MVVM 패턴에 자주 사용되는 RxJava는 무겁고 더이상 필요하지 않아 Kotlin Coroutine 과 LiveData 로 대체되었다.

### Library reference resources:
1. Kotlin Coroutine : https://kotlinlang.org/docs/reference/coroutines-overview.html
2. LiveData: https://developer.android.com/topic/libraries/architecture/livedata
3. ViewModel : https://developer.android.com/topic/libraries/architecture/viewmodel
4. Paging : https://developer.android.com/topic/libraries/architecture/paging
5. Room: https://developer.android.com/topic/libraries/architecture/room.html
6. Dagger2: https://dagger.dev/android
7. Retrofit2: https://square.github.io/retrofit/
8. Glide: https://github.com/bumptech/glide/
