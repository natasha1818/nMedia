package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.ShotNumber
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "05 Марта 2023 15:24",
            contentPost = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растем сами и помогаем расти студентам: от новичков до увлеченных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставит хотеть больше, целиться выше, бежать быстрее. Наша миссия - помочь встать на путь роста и начать цепочку перемен -> https://netology.ru",
            likesByMe = false,
            countLike = 999,
            shareCount = 99,
            viewingCount = 2000
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "06 Марта 2023 10:24",
            contentPost = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
            likesByMe = false,
            countLike = 501,
            shareCount = 1_000,
            viewingCount = 1
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "06 Марта 2023 12:01",
            contentPost = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
            likesByMe = false,
            countLike = 10_000_999,
            shareCount = 100,
            viewingCount = 15
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "07 Марта 2023 02:15",
            contentPost = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
            likesByMe = false,
            countLike = 15,
            shareCount = 1,
            viewingCount = 9
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "09 Марта 2023 15:30",
            contentPost = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
            likesByMe = false,
            countLike = 200,
            shareCount = 99,
            viewingCount = 1_000
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "09 Марта 2023 20:00",
            contentPost = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
            likesByMe = false,
            countLike = 1_000,
            shareCount = 700,
            viewingCount = 999
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "09 Марта 2023 22:01",
            contentPost = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
            likesByMe = false,
            countLike = 15,
            shareCount = 15,
            viewingCount = 20
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "11 Марта 2023 09:15",
            contentPost = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
            likesByMe = false,
            countLike = 99,
            shareCount = 100,
            viewingCount = 555
        ),
        Post(
            idPost = nextId++,
            author = "Нетология.Университет интернет-профессий будущего",
            dataPost = "12 Марта 2023 06:15",
            contentPost = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
            likesByMe = false,
            countLike = 888,
            shareCount = 888,
            viewingCount = 888
        )

    ).reversed()

    private val data = MutableLiveData(posts)
    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.idPost == id) {
                post.copy(
                    likesByMe = !post.likesByMe,
                    countLike = if (post.likesByMe) post.countLike - 1 else post.countLike + 1
                )
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.idPost == id) {
                post.copy(shareCount = post.shareCount + 1)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {
            it.idPost != id
        }
        data.value = posts
    }

    override fun save(post:Post) {
        if (post.idPost == 0L) {
            posts = listOf(post.copy(idPost = nextId++, author = "Me", dataPost = "now")) + posts
            data.value = posts
            return
        }
        posts = posts.map {
            if (it.idPost != post.idPost) it else it.copy(contentPost = post.contentPost)
            }
        data.value = posts
        }

    }
