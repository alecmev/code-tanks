using System.Collections.Generic;

namespace Com.CodeGame.CodeTanks2012.DevKit.CSharpCgdk
{
    public class LimitedQueue<T> : Queue<T>
    {
        private int _limit;
        public int Limit
        {
            get { return _limit; }
        }

        private T _last;
        public T Last
        {
            get { return _last; }
        }

        private T _penultimate;
        public T Penultimate
        {
            get { return _penultimate; }
        }

        public LimitedQueue(int limit)
            : base(limit)
        {
            _limit = limit;
        }

        public LimitedQueue(int limit, T fill)
            : this(limit)
        {
            for (int i = 0; i < _limit; ++i)
                Enqueue(fill);
        }

        public new void Enqueue(T item)
        {
            if (Count >= _limit)
                Dequeue();

            _penultimate = _last;
            _last = item;
            base.Enqueue(item);
        }
    }
}
